package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionDto {
    private Long id;
    @NotBlank
    private String description;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private Set<Long> memberIds;
}
