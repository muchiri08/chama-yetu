package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.service.interfaces.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody @Valid ExpenseDto expenseDto) {
        ExpenseDto responseDto = expenseService.createExpense(expenseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseDto>> findAllExpenses(Pageable pageable) throws NoDataFoundException {
        Page<ExpenseDto> responseDto = expenseService.findAllExpenses(pageable);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> findExpenseById(@PathVariable Long id) throws NoDataFoundException {
        ExpenseDto responseDto = expenseService.findExpenseById(id);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("id") Long id, @RequestBody @Valid ExpenseDto expenseDto) throws NoDataFoundException {
        ExpenseDto responseDto = expenseService.updateExpense(id, expenseDto);

        return ResponseEntity.ok(responseDto);
    }
}
