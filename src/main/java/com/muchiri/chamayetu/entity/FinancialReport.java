package com.muchiri.chamayetu.entity;

import com.muchiri.chamayetu.enums.FinancialReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "financial_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReport implements Serializable {
    @Id
    @SequenceGenerator(
            name = "financial_report_sequence",
            sequenceName = "financial_report_sequence",
            initialValue = 1001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "financial_report_sequence"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private FinancialReportType type;
    private LocalDateTime date;
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<FinancialReportData> data;
}
