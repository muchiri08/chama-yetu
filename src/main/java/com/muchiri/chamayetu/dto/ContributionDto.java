package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributionDto {
    private Long id;

    @NotNull
    private Long memberId;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private LocalDateTime dateTime;

}
