package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.ContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contributions")
public class ContributionController {
    private final ContributionService contributionService;

    @PostMapping("/create")
    public ResponseEntity<ContributionDto> createContribution(@RequestBody @Valid ContributionDto contributionDto) throws NoDataFoundException {
        ContributionDto responseDto = contributionService.createContribution(contributionDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ContributionDto>> getAllContributions(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<ContributionDto> responseDtos = contributionService.getAllContributions(pageable);

        return ResponseEntity.ok(responseDtos);
    }
}
