package com.muchiri.chamayetu.entity;

import com.muchiri.chamayetu.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            initialValue = 1001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long id;
    private LocalDateTime dateTime;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    @JoinColumn(name = "investment_id")
    private Investment investment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
