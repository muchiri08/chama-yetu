package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.service.interfaces.ContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contributions")
public class ContributionController {
    private final ContributionService contributionService;

    @PostMapping("/create")
    public ContributionDto contributionDto(@RequestBody @Valid ContributionDto contributionDto) throws NoDataFoundException {
        ContributionDto responseDto = contributionService.createContribution(contributionDto);

        return responseDto;
    }
}
