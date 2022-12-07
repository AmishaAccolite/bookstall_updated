package com.BookStall.bookstall.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStall.bookstall.Model.Book;
import com.BookStall.bookstall.Model.Inventory;
import com.BookStall.bookstall.Model.Transaction;
import com.BookStall.bookstall.Model.User;
import com.BookStall.bookstall.Model.Wallet;
import com.BookStall.bookstall.Repository.BookRepository;
import com.BookStall.bookstall.Repository.InventoryRepository;
import com.BookStall.bookstall.Repository.TransactionRepository;
import com.BookStall.bookstall.Repository.UserRepository;
import com.BookStall.bookstall.Repository.WalletRepository;
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionrepo;

    @Autowired
    private WalletRepository walletrepo;

    @Autowired
    BookRepository bookrepo;

    @Autowired
    private InventoryRepository invrepo;

    @Autowired
    private UserRepository userrepo;

    @Autowired
    Wallet walletUpdated;

    @Autowired
    Book returnBook;


    public Transaction createTransactionBorrow(int classId, Transaction transaction) {
        Inventory inv = transactionrepo.findInventoryByClassId(classId);
        int bookCount = inv.getBookCount();
        int flag = 0;
        if (bookCount == 0) {
            System.out.println("Books Not Available!");
            flag = 1;
            return null;
        }
        if (flag != 1) {
            Optional<User> user = userrepo.findById(transaction.getUserId());


            if (user.isPresent()) {
                User userObj = user.get();

                int amount = transactionrepo.findWalletBalanceByUserId(userObj.getUserId());
                if (userObj.isUserStatus()) {
                    if (userObj.getBooksBorrowed() < 3) {
                        if (amount >= (0.2 * inv.getBookPrice())) {

                            List<Book> BookList = transactionrepo.findBookByClassId(classId);
                            for (Book book : BookList) {
                                if (!(book.isBookStatus())) {
                                    transaction.setBookId(book.getBookId());
                                    book.setBookStatus(true);
                                    break;
                                }
                            }
                            userObj.setBooksBorrowed(userObj.getBooksBorrowed()+1);
                            userrepo.save(userObj);

                            transaction.setUserId(userObj.getUserId());
                            transaction.setDeposit((int) (0.2 * inv.getBookPrice()));
                            transaction.setRefundBalance((int) (0.1 * inv.getBookPrice()));

                            Wallet userWallet = transactionrepo.findWalletByUserId(userObj.getUserId());
                            walletUpdated.setBalance(amount - transaction.getDeposit());
                            walletUpdated.setWalletId(userWallet.getWalletId());
                            walletUpdated.setUserId(userWallet.getUserId());
                            walletrepo.save(walletUpdated);

                            inv.setBookCount(inv.getBookCount() - 1);
                            invrepo.save(inv);

                            transaction.setRentStatus('B');
                            transaction.setUserWalletBalance(amount - transaction.getDeposit());
                            Transaction transactionAdded = transactionrepo.save(transaction);
                            return transactionAdded;
                        } else {
                            System.out.println("Please Recharge Your Wallet First!!!");
                        }
                    } else {
                        System.out.println("You can Borrow Max 3 Books at a time");
                    }
                } else {
                    System.out.println("AUTHORISATION SUSPENDED!!!");
                }
            } else {
                System.out.println("Please Enter Valid User Details");
            }

        }
        return null;
    }

    public List<Transaction> getUserTransactions(int userId) {
        return transactionrepo.findTransactionByUserId(userId);
    }

    public Transaction createTransactionReturn(int bookId, Transaction transaction) {
        int userObj = transaction.getUserId();
        User user= userrepo.findUserByUserId(userObj);
        user.setUserId(userObj);
        user.setBooksBorrowed(user.getBooksBorrowed()-1);

        userrepo.save(user);

        returnBook.setBookId(bookId);
        returnBook.setClassId(transactionrepo.findClassIdByBookId(bookId));
        returnBook.setBookStatus(false);
        bookrepo.save(returnBook);


        int amount = transactionrepo.findWalletBalanceByUserId(userObj);
        walletUpdated.setWalletId(userObj);
        walletUpdated.setUserId(userObj);
        int refundBalance=transactionrepo.findRefundBalanceBookId(bookId);
        walletUpdated.setBalance(amount+refundBalance);
        walletrepo.save(walletUpdated);


        int returnedBookClass=transactionrepo.findClassIdByBookId(bookId);
        Inventory inv = transactionrepo.findInventoryByClassId(returnedBookClass);
        inv.setBookCount(inv.getBookCount()+1);
        invrepo.save(inv);



        transaction.setBookId(bookId);
        transaction.setRentStatus('R');
        transaction.setUserWalletBalance(amount+refundBalance);
        transaction.setRefundBalance(refundBalance);
        Transaction transactionAdded = transactionrepo.save(transaction);
        return transactionAdded;

    }

    public List<Transaction> getAllTransaction(){
        return this.transactionrepo.findAll();
    }

    public List<Integer> getUserWalletHistory(int userId){
        return transactionrepo.findUserWalletTransactionHistory(userId);
    }
}

