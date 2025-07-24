package com.example.Wallet.userWallet.Interface;

import com.example.Wallet.userWallet.dto.WalletTransaction;
import com.example.Wallet.userWallet.dto.WalletTransfer;

public interface UserwalletInterface
{
    Integer checkBalance(String userId);

    Integer creditAmount(WalletTransfer walletTransfer);

    Integer debitAmount(WalletTransfer walletTransfer);

    Integer performTransaction(WalletTransaction walletTransaction);

}
