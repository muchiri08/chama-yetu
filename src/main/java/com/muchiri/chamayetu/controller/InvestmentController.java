package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.InvestmentDto;
import com.muchiri.chamayetu.service.interfaces.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/create")
    public ResponseEntity<InvestmentDto> createInvestment(@RequestBody @Valid InvestmentDto investmentDto){
        InvestmentDto responseDto = investmentService.createInvestment(investmentDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
