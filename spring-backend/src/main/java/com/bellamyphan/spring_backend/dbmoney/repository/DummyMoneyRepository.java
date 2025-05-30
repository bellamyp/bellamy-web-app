package com.bellamyphan.spring_backend.dbmoney.repository;

import com.bellamyphan.spring_backend.dbmoney.entity.MoneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyMoneyRepository extends JpaRepository<MoneyEntity, Long> {
}
