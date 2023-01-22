package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAll(Pageable pageable);

    Page<Transaction> findTransactionByType(TransactionType type, Pageable pageable);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type AND t.dateTime BETWEEN :startDate AND :endDate")
    BigDecimal getTotalsBetweenDates(@Param("type") TransactionType transactionType, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
