package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.service.interfaces.ExpenseService;
import lombok.RequiredArgsConstructor;
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
}
