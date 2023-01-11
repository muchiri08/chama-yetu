package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.TransactionDto;
import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.enums.TransactionType;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.TransactionNotFoundException;
import com.muchiri.chamayetu.repository.TransactionRepository;
import com.muchiri.chamayetu.service.interfaces.InvestmentService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import com.muchiri.chamayetu.service.interfaces.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final InvestmentService investmentService;
    private final MemberService memberService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, @Lazy InvestmentService investmentService, MemberService memberService) {
        this.transactionRepository = transactionRepository;
        this.investmentService = investmentService;
        this.memberService = memberService;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) throws InvestmentNotFoundException, MemberNotFoundException {
        Transaction transaction = new Transaction();
        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDateTime(transactionDto.getDateTime());
        if (transactionDto.getType() == TransactionType.INVESTMENT) {
            transaction.setInvestment(investmentService.getInvestmentById(transactionDto.getInvestmentId()));
        } else {
            transaction.setInvestment(null);
        }

        transaction.setMember(memberService.getMemberById(transactionDto.getMemberId()));

        transactionRepository.save(transaction);

        return mapTransactionToTransactionDto(transaction);
    }

    @Override
    public TransactionDto findTransactionById(Long id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotFoundException("Transaction with ID " + id + "not found!")
        );
        return mapTransactionToTransactionDto(transaction);
    }

    private TransactionDto mapTransactionToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setType(transaction.getType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setInvestmentId(transaction.getInvestment().getId());
        transactionDto.setMemberId(transaction.getMember().getId());
        transactionDto.setDateTime(transaction.getDateTime());

        return transactionDto;
    }
}
