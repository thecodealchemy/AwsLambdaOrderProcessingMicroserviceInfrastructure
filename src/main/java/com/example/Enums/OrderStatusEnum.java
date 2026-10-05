package com.example.Enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    Success(1.0),
    Failure(0.0);
    private final double value;
    OrderStatusEnum(double value) {
        this.value = value;
    }
}
