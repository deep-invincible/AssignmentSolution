package com.example.Wallet.userWallet;

import com.example.Wallet.userWallet.Interface.UserwalletInterface;
import com.example.Wallet.userWallet.dto.WalletTransaction;
import com.example.Wallet.userWallet.dto.WalletTransfer;
import com.example.Wallet.userWallet.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserWalletServiceImpl implements UserwalletInterface {

    public UserWalletRepo userWalletRepo;

    public UserWalletServiceImpl() {
        userWalletRepo = new UserWalletRepo();
    }

    @Override
    public Integer checkBalance(String userId) {
        try{
            User user = userWalletRepo.fetchUserById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            return user.getWallet().getBalance();
        } catch (Exception e) {
            throw new RuntimeException("failed to load user: " + e.getMessage(), e);
        }


    }

    @Override
    public Integer creditAmount(WalletTransfer walletTransfer) {
        try{
            User user = userWalletRepo.fetchUserById(walletTransfer.getUserId());

            if(user == null){
                throw new IllegalArgumentException("User not found");
            }

            synchronized (user) {
                Integer availableAmount = user.getWallet().getBalance();
                user.getWallet().setBalance(availableAmount + walletTransfer.getAmount());
                return user.getWallet().getBalance();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to credit amount: " + e.getMessage(), e);
        }

    }

    @Override
    public Integer debitAmount(WalletTransfer walletTransfer) {
        try{
            User user = userWalletRepo.fetchUserById(walletTransfer.getUserId());

            if (user == null)
            {
                throw new IllegalArgumentException("User not found");
            }

            synchronized (user) {
                Integer availableAmount = user.getWallet().getBalance();
                if (availableAmount < walletTransfer.getAmount()) {
                    throw new IllegalArgumentException("Insufficient balance");
                }
                user.getWallet().setBalance(availableAmount - walletTransfer.getAmount());
                return user.getWallet().getBalance();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to debit amount: " + e.getMessage(), e);
        }

    }

    @Override
    public Integer performTransaction(WalletTransaction walletTransaction) {
        User fromUser = userWalletRepo.fetchUserById(walletTransaction.getFromUserId());
        User toUser = userWalletRepo.fetchUserById(walletTransaction.getToUserId());

        if (fromUser == null || toUser == null || walletTransaction.getAmount() <= 0) {
            return null;
        }
        Object lock1, lock2;

        if (fromUser.getId() < toUser.getId()) {
            lock1 = fromUser;
            lock2 = toUser;
        } else {
            lock1 = toUser;
            lock2 = fromUser;
        }
        synchronized (lock1) {
            synchronized (lock2) {

                Integer fromBalance = fromUser.getWallet().getBalance();

                if (fromBalance < walletTransaction.getAmount()) {
                    return null;
                }
                fromUser.getWallet().setBalance(fromBalance - walletTransaction.getAmount());

                Integer toBalance = toUser.getWallet().getBalance();
                toUser.getWallet().setBalance(toBalance + walletTransaction.getAmount());

                return fromUser.getWallet().getBalance();
            }
        }
    }
}
