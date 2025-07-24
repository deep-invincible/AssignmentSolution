package com.example.Wallet.userWallet;

import com.example.Wallet.userWallet.entity.User;
import com.example.Wallet.userWallet.entity.Wallet;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserWalletRepo {
    private Map<String, User> internalDb = new ConcurrentHashMap<>();

    public UserWalletRepo() {
        mockDbInit();
    }

    public void mockDbInit() {
        Wallet walletForUser1 = new Wallet(1, 100);
        Wallet walletForUser2 = new Wallet(2, 200);
        User user1 = new User(1, "Deepak", walletForUser1);
        User user2 = new User(2, "Abhishek", walletForUser2);

        this.internalDb.put("1", user1);
        this.internalDb.put("2", user2);
    }

    public User fetchUserById(String id) {
        if (this.internalDb.containsKey(id)) {
            return internalDb.get(id);
        }
        return null;
    }
}
