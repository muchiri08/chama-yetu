package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.TransactionDto;
import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.enums.TransactionType;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.exception.TransactionNotFoundException;
import com.muchiri.chamayetu.repository.TransactionRepository;
import com.muchiri.chamayetu.service.interfaces.InvestmentService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import com.muchiri.chamayetu.service.interfaces.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Transaction transaction = setTransactionFromTransactionDto(transactionDto, new Transaction());
        transaction = transactionRepository.save(transaction);

        return mapTransactionToTransactionDto(transaction);
    }

    @Override
    public TransactionDto findTransactionById(Long id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotFoundException("Transaction with ID " + id + " not found!")
        );
        return mapTransactionToTransactionDto(transaction);
    }

    @Override
    public Page<TransactionDto> findAllTransactions(Pageable pageable) throws TransactionNotFoundException, PageNotFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Transaction> transactions = transactionRepository.findAll(sortedPageable);

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("Not transactions found!");
        }
        if (sortedPageable.getPageNumber() > transactions.getTotalPages()) {
            throw new PageNotFoundException("Invalid page number");
        }
        if (sortedPageable.getPageSize() < 1) {
            throw new PageNotFoundException("Invalid page size");
        }

        return transactions.map(this::mapTransactionToTransactionDto);
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto transactionDto) throws TransactionNotFoundException, InvestmentNotFoundException, MemberNotFoundException {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotFoundException("Transaction with ID " + id + " not found!")
        );
        setTransactionFromTransactionDto(transactionDto, transaction);

        transaction = transactionRepository.save(transaction);

        return mapTransactionToTransactionDto(transaction);
    }

    @Override
    public String deleteTransaction(Long id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotFoundException("Transaction with ID " + id + " not found!")
        );
        transactionRepository.delete(transaction);

        Transaction deletedTransaction = transactionRepository.findById(id).orElse(null);

        return deletedTransaction == null ? "Transaction deleted successfully" : "Transaction with ID " + id + " not deleted!";
    }

    private Transaction setTransactionFromTransactionDto(TransactionDto transactionDto, Transaction transaction) throws InvestmentNotFoundException, MemberNotFoundException {
        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDateTime(transactionDto.getDateTime());
        if (transactionDto.getType() == TransactionType.INVESTMENT) {
            transaction.setInvestment(investmentService.getInvestmentById(transactionDto.getInvestmentId()));
        } else {
            transaction.setInvestment(null);
        }
        transaction.setMember(memberService.getMemberById(transactionDto.getMemberId()));
        return transaction;
    }


    private TransactionDto mapTransactionToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setType(transaction.getType());
        transactionDto.setAmount(transaction.getAmount());
        if (transaction.getInvestment() == null) {
            transactionDto.setInvestmentId(null);
        } else {
            transactionDto.setInvestmentId(transaction.getInvestment().getId());
        }
        transactionDto.setMemberId(transaction.getMember().getId());
        transactionDto.setDateTime(transaction.getDateTime());

        return transactionDto;
    }
}
