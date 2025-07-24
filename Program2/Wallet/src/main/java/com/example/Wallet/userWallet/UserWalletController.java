package com.example.Wallet.userWallet;

import com.example.Wallet.userWallet.Interface.UserwalletInterface;
import com.example.Wallet.userWallet.dto.WalletTransaction;
import com.example.Wallet.userWallet.dto.WalletTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserWalletController {

    private final UserwalletInterface userWalletService;

    public UserWalletController(UserwalletInterface userWalletService) {
        this.userWalletService = new UserWalletServiceImpl();
    }

    @PostMapping("/credit")
    public ResponseEntity credit(@RequestBody WalletTransfer walletTransfer){
        return ResponseEntity.ok(userWalletService.creditAmount(walletTransfer));
    }

    @PostMapping("/debit")
    public ResponseEntity debit(@RequestBody WalletTransfer walletTransfer ){
        return ResponseEntity.ok(userWalletService.debitAmount(walletTransfer));
    }

    @GetMapping("/checkBalance/{userId}")
    public ResponseEntity checkBalance(@PathVariable String userId){

        return ResponseEntity.ok(userWalletService.checkBalance(userId));
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> performTransaction(@RequestBody WalletTransaction walletTransaction) {
        Integer result = userWalletService.performTransaction(walletTransaction);
        if (result == null) {
            return ResponseEntity.badRequest().body("Transaction failed due to insufficient funds or invalid users.");
        }
        return ResponseEntity.ok("Transaction successful. New balance: " + result);
    }

}
