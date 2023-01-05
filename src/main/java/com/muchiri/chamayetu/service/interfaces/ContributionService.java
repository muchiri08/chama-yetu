package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ContributionService {
    ContributionDto createContribution(ContributionDto contributionDto) throws MemberNotFoundException;
    Page<ContributionDto> getAllContributions(Pageable pageable) throws PageNotFoundException, NoDataFoundException;
    ContributionDto findContributionById(Long id) throws NoDataFoundException;
    ContributionDto updateContribution(Long id, ContributionDto contributionDto) throws MemberNotFoundException;
    String deleteContribution(Long id) throws NoDataFoundException;
    Page<ContributionDto> findContributionByDateTimeBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable) throws PageNotFoundException, NoDataFoundException;
    Page<ContributionDto> findByMemberId(Long id, Pageable pageable) throws PageNotFoundException, NoDataFoundException;
}
