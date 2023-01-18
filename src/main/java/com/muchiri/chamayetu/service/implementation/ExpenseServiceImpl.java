package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.ExpenseDto;
import com.muchiri.chamayetu.entity.Expense;
import com.muchiri.chamayetu.enums.ExpenseType;
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

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) throws NoDataFoundException {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Expense with ID " + id + " not found!")
        );
        expense.setType(expenseDto.getType());
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expense.setDateTime(expenseDto.getDateTime());

        Expense updatedExpense = expenseRepository.save(expense);

        return modelMapper.map(updatedExpense, ExpenseDto.class);
    }

    @Override
    public String deleteExpense(Long id) throws NoDataFoundException {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Expense with ID " + id + " not found!")
        );
        expenseRepository.delete(expense);

        Expense deletedExpense = expenseRepository.findById(id).orElse(null);

        return deletedExpense == null ? "Expense deleted successfully" : "Expense with ID " + id + " not deleted!";
    }

    @Override
    public Page<ExpenseDto> findExpensesByType(String expenseType, Pageable pageable) throws NoDataFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        ExpenseType type;
        try {
            type = ExpenseType.valueOf(expenseType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoDataFoundException("Invalid expense of type: " + expenseType);
        }
        Page<Expense> expenses = expenseRepository.findExpensesByType(type, sortedPageable);

        if (expenses.isEmpty()) {
            throw new NoDataFoundException("No expenses of type: " + type + " found!");
        }

        return expenses.map(expense -> modelMapper.map(expense, ExpenseDto.class));
    }
}
