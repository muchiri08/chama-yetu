package com.muchiri.chamayetu.entity;

import com.muchiri.chamayetu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "decisions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Decision implements Serializable {
    @Id
    @SequenceGenerator(
            name = "decision_sequence",
            sequenceName = "decision_sequence",
            initialValue = 101,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "decision_sequence"
    )
    private Long id;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
