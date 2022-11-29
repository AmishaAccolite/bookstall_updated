package com.BookStall.bookstall.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookStall.bookstall.Model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}