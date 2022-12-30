package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.enums.FinancialReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReportDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FinancialReportType type;
    private LocalDateTime date;
    private List<FinancialReportDataDto> data;
}
