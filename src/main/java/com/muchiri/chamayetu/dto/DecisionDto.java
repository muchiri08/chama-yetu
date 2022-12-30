package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionDto {
    private Long id;
    private String description;
    private LocalDateTime dateTime;
    private MemberDto member;
}
