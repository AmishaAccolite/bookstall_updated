package com.BookStall.bookstall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookStall.bookstall.Model.User;
import com.BookStall.bookstall.Model.Wallet;
import com.BookStall.bookstall.Service.WalletService;


@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PutMapping("/users/addwallet/{walletId}")
    private ResponseEntity<Wallet> updateUser(@PathVariable int walletId, @RequestBody Wallet wallet)
    {
        wallet.setWalletId(walletId);
        return ResponseEntity.ok().body(this.walletService.addAmount(wallet));
    }

}

