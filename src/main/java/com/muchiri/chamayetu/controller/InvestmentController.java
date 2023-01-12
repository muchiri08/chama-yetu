package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.InvestmentDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/create")
    public ResponseEntity<InvestmentDto> createInvestment(@RequestBody @Valid InvestmentDto investmentDto) {
        InvestmentDto responseDto = investmentService.createInvestment(investmentDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<InvestmentDto>> getAllInvestments(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<InvestmentDto> responseDto = investmentService.getAllInvestments(pageable);

        return ResponseEntity.ok(responseDto);
    }
}
