package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.entity.Expense;
import com.muchiri.chamayetu.repository.ExpenseRepository;
import com.muchiri.chamayetu.service.interfaces.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = modelMapper.map(expenseDto, Expense.class);

        expenseRepository.save(expense);
        ExpenseDto responseExpense = modelMapper.map(expense, ExpenseDto.class);

        return responseExpense;
    }

}
