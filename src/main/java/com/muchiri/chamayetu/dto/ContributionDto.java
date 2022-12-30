package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributionDto {
    private Long id;
    private MemberDto member;
    private BigDecimal amount;
    private LocalDateTime dateTime;

}
