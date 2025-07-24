package com.example.Wallet.userWallet.entity;

import lombok.*;

public class User
{
    private Integer id;
    private String userName;
    private Wallet wallet;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public User(Integer id, String userName, Wallet wallet){
        this.id = id;
        this.userName = userName;
        this.wallet = wallet;
    }

}
