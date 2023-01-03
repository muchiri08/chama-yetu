package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;

public interface ContributionService {
    ContributionDto createContribution(ContributionDto contributionDto) throws NoDataFoundException;
}
