package com.muchiri.chamayetu.entity;

import com.muchiri.chamayetu.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            initialValue = 1001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String occupation;
    private String location;
    @OneToMany(mappedBy = "member")
    private List<Contribution> contributions;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
