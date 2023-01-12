package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.InvestmentDto;
import com.muchiri.chamayetu.entity.Investment;
import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.repository.InvestmentRepository;
import com.muchiri.chamayetu.service.interfaces.InvestmentService;
import com.muchiri.chamayetu.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final TransactionService transactionService;

    @Override
    public InvestmentDto createInvestment(InvestmentDto investmentDto) {
        Investment investment = new Investment();
        investment.setType(investmentDto.getType());
        investment.setAmountInvested(investmentDto.getAmountInvested());
        investment.setReturnOfInvestment(investmentDto.getReturnOfInvestment());
        investment.setDate(investmentDto.getDate());

        investmentRepository.save(investment);

        return mapInvestmentToInvestmentDto(investment);
    }

    @Override
    public Investment getInvestmentById(Long id) throws InvestmentNotFoundException {
        Investment investment = investmentRepository.findById(id).orElseThrow(
                () -> new InvestmentNotFoundException("Investment with ID " + id + " not found!")
        );
        return investment;
    }

    @Override
    public Page<InvestmentDto> getAllInvestments(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Investment> investments = investmentRepository.findAll(sortedPageable);
        int totalPages = investments.getTotalPages();

        if (pageable.getPageSize() < 0 || pageable.getPageNumber() > totalPages) {
            throw new PageNotFoundException("Invalid Page Size or Page Number");
        }

        if (investments.getNumberOfElements() == 0) {
            throw new NoDataFoundException("No data found!");
        }

        return investments.map(this::mapInvestmentToInvestmentDto);
    }

    private InvestmentDto mapInvestmentToInvestmentDto(Investment investment) {
        InvestmentDto investmentDto = new InvestmentDto();
        investmentDto.setId(investment.getId());
        investmentDto.setType(investment.getType());
        investmentDto.setAmountInvested(investment.getAmountInvested());
        investmentDto.setReturnOfInvestment(investment.getReturnOfInvestment());
        investmentDto.setDate(investment.getDate());
        if (investment.getTransactions() == null) {
            investmentDto.setTransactionsIds(null);
        } else {
            investmentDto.setTransactionsIds(investment.getTransactions().stream().map(Transaction::getId).collect(Collectors.toList()));
        }
        return investmentDto;
    }
}
