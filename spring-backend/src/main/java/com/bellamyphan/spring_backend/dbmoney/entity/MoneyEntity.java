package com.bellamyphan.spring_backend.dbmoney.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MoneyEntity {
    @Id
    private Long id;
}