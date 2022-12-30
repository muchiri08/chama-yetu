package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
