package com.busbooking.service;

import com.busbooking.dao.LoyaltyTransactionRepository;
import com.busbooking.dao.UserRepository;
import com.busbooking.model.LoyaltyTransaction;
import com.busbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SuppressWarnings("null")
public class LoyaltyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoyaltyTransactionRepository transactionRepository;

    @Transactional
    public Integer earnPointsFromBooking(Long userId, Double amountSpent) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        int pointsEarned = (int) (amountSpent / 10);
        if (pointsEarned > 0) {
            int currentPoints = user.getLoyaltyPoints() != null ? user.getLoyaltyPoints() : 0;
            user.setLoyaltyPoints(currentPoints + pointsEarned);
            userRepository.save(user);

            LoyaltyTransaction tx = LoyaltyTransaction.builder()
                    .userId(userId)
                    .points(pointsEarned)
                    .description("Earned from booking (spent ₹" + amountSpent + ")")
                    .build();
            transactionRepository.save(tx);
        }
        return user.getLoyaltyPoints();
    }

    @Transactional
    public Integer redeemPoints(Long userId, Integer pointsToRedeem) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        int currentPoints = user.getLoyaltyPoints() != null ? user.getLoyaltyPoints() : 0;
        if (currentPoints < pointsToRedeem) {
            throw new RuntimeException("Insufficient loyalty points");
        }
        
        user.setLoyaltyPoints(currentPoints - pointsToRedeem);
        userRepository.save(user);

        LoyaltyTransaction tx = LoyaltyTransaction.builder()
                .userId(userId)
                .points(-pointsToRedeem)
                .description("Redeemed points for discount")
                .build();
        transactionRepository.save(tx);

        return user.getLoyaltyPoints();
    }

    public Integer getBalance(Long userId) {
        return userRepository.findById(userId)
                .map(u -> u.getLoyaltyPoints() != null ? u.getLoyaltyPoints() : 0)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<LoyaltyTransaction> getHistory(Long userId) {
        return transactionRepository.findByUserIdOrderByTimestampDesc(userId);
    }
}
