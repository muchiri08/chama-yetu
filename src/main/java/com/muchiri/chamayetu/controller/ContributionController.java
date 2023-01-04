package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.ContributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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

    @GetMapping("/{id}")
    public ResponseEntity<ContributionDto> findContributionById(@PathVariable("id") Long id) throws NoDataFoundException {
        ContributionDto responseDto = contributionService.findContributionById(id);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContributionDto> updateContribution(@PathVariable("id") Long id, @RequestBody @Valid ContributionDto contributionDto) throws NoDataFoundException {
        ContributionDto responseDto = contributionService.updateContribution(id, contributionDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContribution(@PathVariable("id") Long id) throws NoDataFoundException {
        String responseString = contributionService.deleteContribution(id);

        return ResponseEntity.ok(responseString);
    }

    @GetMapping("/daterange/{fromDate}/{toDate}")
    public ResponseEntity<Page<ContributionDto>> findContributionByDateTimeBetween(@PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, @PathVariable("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate, Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<ContributionDto> responseDto = contributionService.findContributionByDateTimeBetween(fromDate, toDate, pageable);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Page<ContributionDto>> findContributionByMemberId(@PathVariable("id") Long id, Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<ContributionDto> responseDto = contributionService.findByMemberId(id, pageable);

        return ResponseEntity.ok(responseDto);
    }
}
