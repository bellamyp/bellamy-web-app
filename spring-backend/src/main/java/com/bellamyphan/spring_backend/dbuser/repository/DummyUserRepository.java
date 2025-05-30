package com.bellamyphan.spring_backend.dbuser.repository;

import com.bellamyphan.spring_backend.dbuser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyUserRepository extends JpaRepository<UserEntity, Long> {
}
