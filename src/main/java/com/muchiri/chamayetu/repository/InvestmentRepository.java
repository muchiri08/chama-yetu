package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Investment;
import com.muchiri.chamayetu.enums.InvestmentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Page<Investment> findAll(Pageable pageable);
    @Query("SELECT i FROM Investment i WHERE i.type = :type")
    Page<Investment> findInvestmentByType(@Param("type") InvestmentType type, Pageable pageable);
}
