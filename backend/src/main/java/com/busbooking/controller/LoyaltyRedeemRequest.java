package com.busbooking.controller;

import lombok.Data;

@Data
public class LoyaltyRedeemRequest {
    private Long userId;
    private Integer points;
}
