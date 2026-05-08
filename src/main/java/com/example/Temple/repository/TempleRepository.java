package com.example.Temple.repository;

import com.example.Temple.model.Temple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempleRepository extends JpaRepository<Temple, Long> {
}
