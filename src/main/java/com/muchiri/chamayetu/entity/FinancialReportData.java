package com.muchiri.chamayetu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "financial_report_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReportData implements Serializable {

    @Id
    @SequenceGenerator(
            name = "financial_report_data_sequence",
            sequenceName = "financial_report_data_sequence",
            initialValue = 1001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "financial_report_data_sequence"
    )
    private Long id;
    private BigDecimal value;
    private String description;
    @ManyToOne
    @JoinColumn(name = "financial_report_id")
    private FinancialReport report;

}
