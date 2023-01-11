package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.TransactionDto;
import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto) throws InvestmentNotFoundException, MemberNotFoundException;
    TransactionDto findTransactionById(Long id) throws TransactionNotFoundException;
}
