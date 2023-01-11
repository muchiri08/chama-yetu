package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.InvestmentDto;
import com.muchiri.chamayetu.entity.Investment;
import com.muchiri.chamayetu.exception.InvestmentNotFoundException;

public interface InvestmentService {
    InvestmentDto createInvestment(InvestmentDto investmentDto);
    Investment getInvestmentById(Long id) throws InvestmentNotFoundException;
}
