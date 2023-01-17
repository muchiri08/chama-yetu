package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);

    Page<ExpenseDto> findAllExpenses(Pageable pageable) throws NoDataFoundException;

    ExpenseDto findExpenseById(Long id) throws NoDataFoundException;

    ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) throws NoDataFoundException;

    String deleteExpense(Long id) throws NoDataFoundException;
}
