package com.server.dori.domain.test.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.dori.domain.test.entity.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
