package com.muchiri.chamayetu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "meetings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    @Id
    @SequenceGenerator(
            name = "meeting_sequence",
            sequenceName = "meeting_sequence",
            initialValue = 101,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meeting_sequence"
    )
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String notes;
    @ManyToMany
    @JoinTable(
            name = "meetings_members",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;
}
