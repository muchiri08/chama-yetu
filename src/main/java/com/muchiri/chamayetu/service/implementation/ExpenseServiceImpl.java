package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.entity.Expense;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.repository.ExpenseRepository;
import com.muchiri.chamayetu.service.interfaces.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<ExpenseDto> findAllExpenses(Pageable pageable) throws NoDataFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Expense> expenses = expenseRepository.findAll(sortedPageable);

        if (expenses.isEmpty()) {
            throw new NoDataFoundException("No expenses found");
        }

        return expenses.map(expense -> modelMapper.map(expense, ExpenseDto.class));
    }

    @Override
    public ExpenseDto findExpenseById(Long id) throws NoDataFoundException {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Expense with ID " + id + " not found!")
        );

        return modelMapper.map(expense, ExpenseDto.class);
    }
}
