package com.example.Wallet.userWallet.dto;

public class WalletTransfer
{
    private String userId;
    private Integer amount;

    public String getUserId() {
        return userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
