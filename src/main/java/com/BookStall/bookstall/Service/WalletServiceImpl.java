package com.BookStall.bookstall.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStall.bookstall.Exceptions.NoObjectFoundException;
import com.BookStall.bookstall.Model.User;
import com.BookStall.bookstall.Model.Wallet;
import com.BookStall.bookstall.Repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletrepo;

    public Wallet addAmount(Wallet wallet) {
        Optional<Wallet> walletObj = this.walletrepo.findById(wallet.getWalletId());

        if (walletObj.isPresent() && wallet.getBalance()>0 && wallet.getBalance() % 500 == 0) {
            Wallet walletUpdate = walletObj.get();
            walletUpdate.setBalance(walletUpdate.getBalance() + wallet.getBalance());
            walletrepo.save(walletUpdate);
            return walletUpdate;
        } else {
            throw new NoObjectFoundException("No valid fields");
        }
    }
}
