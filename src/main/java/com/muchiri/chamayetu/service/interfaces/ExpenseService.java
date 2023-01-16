package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);
    Page<ExpenseDto> findAllExpenses(Pageable pageable) throws NoDataFoundException;
}
