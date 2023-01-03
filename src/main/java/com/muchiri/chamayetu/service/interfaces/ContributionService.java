package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContributionService {
    ContributionDto createContribution(ContributionDto contributionDto) throws NoDataFoundException;
    Page<ContributionDto> getAllContributions(Pageable pageable) throws PageNotFoundException, NoDataFoundException;
    ContributionDto findContributionById(Long id) throws NoDataFoundException;
    ContributionDto updateContribution(Long id, ContributionDto contributionDto) throws NoDataFoundException;
}
