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
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionDto {
    private Long id;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDateTime dateTime;
    private MemberDto member;
}
