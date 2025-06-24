package com.bellamyphan.spring_backend.dbmoney.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction_types")
@Data // Lombok will generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type", nullable = false, unique = true)
    @NotBlank
    private String type;

    // Constructor with parameters excluding id
    public TransactionType(String type) {
        this.type = type;
    }
}