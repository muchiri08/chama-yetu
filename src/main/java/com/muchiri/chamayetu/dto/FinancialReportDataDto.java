package com.muchiri.chamayetu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReportDataDto {
    private Long id;
    private BigDecimal value;
    private String description;
    private FinancialReportDto report;
}
