package com.BookStall.bookstall.Service;

import java.util.List;

import com.BookStall.bookstall.Model.Inventory;
import com.BookStall.bookstall.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    Transaction createTransactionBorrow(int classId, Transaction transaction);

    Transaction createTransactionReturn(int bookId,Transaction transaction);

    List<Transaction> getUserTransactions(int userId);

    List<Transaction> getAllTransaction();

    List<Integer> getUserWalletHistory(int userId);


}

