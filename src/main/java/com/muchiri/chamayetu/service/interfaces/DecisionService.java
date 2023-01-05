package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.DecisionDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;

public interface DecisionService {
    DecisionDto createDecision(DecisionDto decisionDto) throws MemberNotFoundException;
}
