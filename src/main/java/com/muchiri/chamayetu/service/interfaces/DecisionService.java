package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.DecisionDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DecisionService {
    DecisionDto createDecision(DecisionDto decisionDto) throws MemberNotFoundException;
    Page<DecisionDto> getAllDecisions(Pageable pageable) throws PageNotFoundException, NoDataFoundException;
    DecisionDto findDecisionById(Long id) throws NoDataFoundException;
    DecisionDto updateDecision(Long id, DecisionDto decisionDto) throws NoDataFoundException, MemberNotFoundException;
    String deleteDecision(Long id) throws NoDataFoundException;
}
