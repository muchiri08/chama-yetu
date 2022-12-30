package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.enums.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;
    private LocalDateTime dateTime;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private InvestmentDto investment;
    private MemberDto member;
}
