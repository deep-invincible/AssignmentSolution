package com.example.Wallet.userWallet.entity;

import lombok.*;


@Data
public class Wallet
{
    private Integer id;
    private Integer balance;

    public Integer getId() {
        return id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Wallet(Integer id, Integer balance){
        this.id = id;
        this.balance =balance;
    }
}
