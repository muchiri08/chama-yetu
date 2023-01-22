package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Expense;
import com.muchiri.chamayetu.enums.ExpenseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findAll(Pageable pageable);

    Page<Expense> findExpensesByType(ExpenseType type, Pageable pageable);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.dateTime BETWEEN :startDateTime AND :endDateTime")
    BigDecimal getTotalExpensesBetweenDates(@Param("startDateTime") LocalDateTime startDate, @Param("endDateTime") LocalDateTime endDate);
}
