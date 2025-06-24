package com.bellamyphan.spring_backend.dbmoney.entity;

import lombok.Getter;

@Getter
public enum TransactionTypeEnum {
    INCOME("Income"),
    INCOME_TAX("Income Tax"),
    INVEST("Invest"),
    SAVINGS("Savings"),
    HEALTH("Health"),
    COLLEGE_WORK("College/Work"),
    CAR("Car"),
    GAS("Gas"),
    GROCERY("Grocery"),
    GOVERNMENT("Government"),
    HOUSING("Housing"),
    UTILITY("Utility"),
    PHONE("Phone"),
    PET("Pet"),
    ENTERTAINMENT("Entertainment"),
    MEAL("Meal"),
    SHOP("Shop"),
    CLOTHES("Clothes"),
    CHARITY("Charity");

    private final String displayName;

    TransactionTypeEnum(String displayName) {
        this.displayName = displayName;
    }
}