package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.InvestmentDto;
import com.muchiri.chamayetu.entity.Investment;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvestmentService {
    InvestmentDto createInvestment(InvestmentDto investmentDto);
    Investment getInvestmentById(Long id) throws InvestmentNotFoundException;
    Page<InvestmentDto> getAllInvestments(Pageable pageable) throws PageNotFoundException, NoDataFoundException;
}
