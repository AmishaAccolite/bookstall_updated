package com.BookStall.bookstall.Controller;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookStall.bookstall.Exceptions.NoObjectFoundException;
import com.BookStall.bookstall.Model.Transaction;
import com.BookStall.bookstall.Model.User;
import com.BookStall.bookstall.Service.TransactionService;
@EnableTransactionManagement
@RestController

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow/{classId}")
    private ResponseEntity<Transaction> borrowBook(@PathVariable int classId, @RequestBody Transaction transaction)
    {
        try {
            System.out.println(transaction);
            return ResponseEntity.ok().body(this.transactionService.createTransactionBorrow(classId, transaction));
        }
        catch (Exception e) {
            throw new NoObjectFoundException("Check input data!");
        }
    }
    @GetMapping("/transaction/{userId}")
    private ResponseEntity<List<Transaction>> getTransactions(@PathVariable int userId)
    {
        return ResponseEntity.ok().body(this.transactionService.getUserTransactions(userId));
    }

    @PostMapping("/return/{bookId}")
    private ResponseEntity<Transaction> returnBook(@PathVariable int bookId, @RequestBody Transaction transaction)
    {
        try {
            System.out.println(transaction);
            return ResponseEntity.ok().body(this.transactionService.createTransactionReturn(bookId, transaction));
        }
        catch (Exception e) {
            throw new NoObjectFoundException("Check input data!");
        }
    }

    @GetMapping("/alltransactions")
    private ResponseEntity<List<Transaction>> getAllTransactions()

    {
        return ResponseEntity.ok().body(this.transactionService.getAllTransaction());
    }

    @GetMapping("/userWallet/{userId}")
    private ResponseEntity<List<Integer>> getWalletHistory(@PathVariable int userId)

    {
        return ResponseEntity.ok().body(this.transactionService.getUserWalletHistory(userId));
    }
}
