package com.busbooking.service;

import com.busbooking.dao.UserRepository;
import com.busbooking.dao.WalletTransactionRepository;
import com.busbooking.model.User;
import com.busbooking.model.WalletTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SuppressWarnings("null")
public class WalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletTransactionRepository transactionRepository;

    @Transactional
    public Double recharge(Long userId, Double amount, String paymentMethod) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Double currentBalance = user.getBalance() != null ? user.getBalance() : 0.0;
        user.setBalance(currentBalance + amount);
        userRepository.save(user);

        WalletTransaction tx = WalletTransaction.builder()
                .userId(userId)
                .amount(amount)
                .type("CREDIT")
                .description("Recharge via " + paymentMethod)
                .build();
        transactionRepository.save(tx);

        return user.getBalance();
    }

    public Double getBalance(Long userId) {
        return userRepository.findById(userId)
                .map(u -> u.getBalance() != null ? u.getBalance() : 0.0)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<WalletTransaction> getTransactions(Long userId) {
        return transactionRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    @Transactional
    public Double deduct(Long userId, Double amount, String description) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Double currentBalance = user.getBalance() != null ? user.getBalance() : 0.0;
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient wallet balance");
        }
        
        user.setBalance(currentBalance - amount);
        userRepository.save(user);

        WalletTransaction tx = WalletTransaction.builder()
                .userId(userId)
                .amount(amount)
                .type("DEBIT")
                .description(description)
                .build();
        transactionRepository.save(tx);

        return user.getBalance();
    }
}
