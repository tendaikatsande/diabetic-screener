package com.zimttech.diabeticscreener.repository;

import com.zimttech.diabeticscreener.entity.Vital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface VitalsRepository extends JpaRepository<Vital, Long> {
    List<Vital> findByCreatedDate(Instant asAt);
}