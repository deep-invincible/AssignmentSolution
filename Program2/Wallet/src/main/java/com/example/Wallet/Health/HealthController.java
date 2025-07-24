package com.example.Wallet.Health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController
{
   @GetMapping("/health")
    public ResponseEntity checkHealth(){
       return ResponseEntity.ok("Working");
   }
}
