package com.muchiri.chamayetu.entity;

import com.muchiri.chamayetu.enums.InvestmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "investments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investment implements Serializable {
    @Id
    @SequenceGenerator(
            name = "investment_sequence",
            sequenceName = "investment_sequence",
            initialValue = 101,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "investment_sequence"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private InvestmentType type;
    private LocalDate date;
    private BigDecimal amountInvested;
    private BigDecimal returnOfInvestment;
    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
