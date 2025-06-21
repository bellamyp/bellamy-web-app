package com.bellamyphan.spring_backend.dbmoney.repository;

import com.bellamyphan.spring_backend.dbmoney.entity.BankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankTypeRepository extends JpaRepository<BankType, Long> {
    // Jpa will generate basic CRUD methods here.

    Optional<BankType> findByType(String type);

    Optional<BankType> findByTypeIgnoreCase(String type);
}