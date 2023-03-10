package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.InvestmentDto;
import com.muchiri.chamayetu.entity.Investment;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvestmentService {
    InvestmentDto createInvestment(InvestmentDto investmentDto);
    Investment getInvestmentById(Long id) throws InvestmentNotFoundException;
    Page<InvestmentDto> getAllInvestments(Pageable pageable) throws PageNotFoundException, InvestmentNotFoundException;
    InvestmentDto updateInvestment(Long id, InvestmentDto investmentDto) throws InvestmentNotFoundException;
    InvestmentDto findInvestmentById(Long id) throws InvestmentNotFoundException;
    String deleteInvestment(Long id) throws InvestmentNotFoundException;
    Page<InvestmentDto> findInvestmentsByType(String investmentType, Pageable pageable) throws InvestmentNotFoundException, PageNotFoundException;
}
