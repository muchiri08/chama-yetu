package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.DecisionDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/decisions")
public class DecisionController {
    private final DecisionService decisionService;

    @PostMapping("/create")
    public ResponseEntity<DecisionDto> createDecision(@RequestBody @Valid DecisionDto decisionDto) throws MemberNotFoundException {
        DecisionDto responseDto = decisionService.createDecision(decisionDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DecisionDto>> getAllDecisions(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<DecisionDto> responseDto = decisionService.getAllDecisions(pageable);

        return ResponseEntity.ok(responseDto);
    }
}
