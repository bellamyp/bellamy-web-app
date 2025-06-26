package com.bellamyphan.spring_backend.dbmoney.entity;

import lombok.Getter;

@Getter
public enum BankTypeEnum {
    CHECKING("Checking"),
    SAVINGS("Savings"),
    CREDIT("Credit"),
    REWARDS("Rewards");

    private final String type;

    BankTypeEnum(String type) {
        this.type = type;
    }

    public String getDisplayName() {
        return type;
    }
}
