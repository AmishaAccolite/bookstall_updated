package com.BookStall.bookstall.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BookStall.bookstall.Model.Book;
import com.BookStall.bookstall.Model.Inventory;
import com.BookStall.bookstall.Model.Transaction;
import com.BookStall.bookstall.Model.Wallet;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

//    @Query("Select t.bookCount from Inventory t where t.classId=?1")
//    int findTotalBooksAvailable(int classId);

    @Query("Select t from Transaction t where t.userId=?1")
    List<Transaction> findTransactionByUserId(int userId);

    @Query("Select t.balance from Wallet t where t.userId=?1")
    int findWalletBalanceByUserId(int userId);


    @Query("Select t from Wallet t where t.userId=?1")
    Wallet findWalletByUserId(int userId);

    @Query("Select b.classId from Book b where b.bookId=?1")
    int findClassIdByBookId(int bookId);

    @Query("Select i from Inventory i where i.classId=?1")
    Inventory findInventoryByClassId(int classId);

//	@Query("Select b.bookId from Book b where b.classId=?1")
//	int findBookIdByClassId(int classId);

    @Query("Select t.refundBalance from Transaction t where t.bookId=?1")
    int findRefundBalanceBookId(int bookId);
    @Query("Select b from Book b where b.classId=?1")
    List<Book> findBookByClassId(int classId);

    @Query("Select b from Book b where b.bookId=?1")
    Book findBookByBookId(int bookId);

    @Query("Select t.userWalletBalance from Transaction t where t.userId=?1")
    List<Integer> findUserWalletTransactionHistory(int userId);

}
