package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.TransactionDto;
import com.muchiri.chamayetu.enums.TransactionType;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.exception.TransactionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto) throws InvestmentNotFoundException, MemberNotFoundException;

    TransactionDto findTransactionById(Long id) throws TransactionNotFoundException;

    Page<TransactionDto> findAllTransactions(Pageable pageable) throws TransactionNotFoundException, PageNotFoundException;

    TransactionDto updateTransaction(Long id, TransactionDto transactionDto) throws TransactionNotFoundException, InvestmentNotFoundException, MemberNotFoundException;

    String deleteTransaction(Long id) throws TransactionNotFoundException;

    Page<TransactionDto> findByTransactionType(String transactionType, Pageable pageable) throws TransactionNotFoundException, PageNotFoundException;

    BigDecimal getTotalWithdrawalsBetweenDates(TransactionType transactionType, LocalDate startDate, LocalDate endDate);

    BigDecimal getTotalInvestmentsBetweenDates(TransactionType transactionType, LocalDate startDate, LocalDate endDate);

    BigDecimal getTotalInterestsBetweenDates(TransactionType transactionType, LocalDate startDate, LocalDate endDate);
}
