package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.TransactionDto;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.exception.TransactionNotFoundException;
import com.muchiri.chamayetu.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody @Valid TransactionDto transactionDto) throws InvestmentNotFoundException, MemberNotFoundException {
        TransactionDto responseDto = transactionService.createTransaction(transactionDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findTransactionById(@PathVariable Long id) throws TransactionNotFoundException {
        TransactionDto responseDto = transactionService.findTransactionById(id);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDto>> findAllTransactions(Pageable pageable) throws PageNotFoundException, TransactionNotFoundException {
        Page<TransactionDto> responseDto = transactionService.findAllTransactions(pageable);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable("id") Long id, @RequestBody @Valid TransactionDto transactionDto) throws InvestmentNotFoundException, TransactionNotFoundException, MemberNotFoundException {
        TransactionDto responseDto = transactionService.updateTransaction(id, transactionDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id) throws TransactionNotFoundException {
        String response = transactionService.deleteTransaction(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<TransactionDto>> findByTransactionType(@PathVariable("type") String type, Pageable pageable) throws PageNotFoundException, TransactionNotFoundException {
        Page<TransactionDto> responseDto = transactionService.findByTransactionType(type, pageable);

        return ResponseEntity.ok(responseDto);
    }
}
