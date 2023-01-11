package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.entity.Transaction;
import com.muchiri.chamayetu.enums.InvestmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDto {
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private InvestmentType type;
    @NotNull
    private LocalDate date;
    @NotNull
    private BigDecimal amountInvested;
    @NotNull
    private BigDecimal returnOfInvestment;
    private List<Long> transactionsIds;
}
