package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAll(Pageable pageable);
    Page<Transaction> findTransactionByType(TransactionType type, Pageable pageable);
}
