package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Expense;
import com.muchiri.chamayetu.enums.ExpenseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findAll(Pageable pageable);
    Page<Expense> findExpensesByType(ExpenseType type, Pageable pageable);
}
