package com.busbooking.controller;

import lombok.Data;

@Data
public class WalletRequest {
    private Long userId;
    private Double amount;
    private String paymentMethod;
}
