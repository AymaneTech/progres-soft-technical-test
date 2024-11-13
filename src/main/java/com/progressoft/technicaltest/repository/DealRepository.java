package com.progressoft.technicaltest.repository;

import com.progressoft.technicaltest.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DealRepository extends JpaRepository<Deal, UUID> {
}
