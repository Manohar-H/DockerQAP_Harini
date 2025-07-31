package com.golfclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;

    private LocalDate membershipStartDate;
    private int membershipDurationInMonths;

    @ManyToMany(mappedBy = "members")
    private List<Tournament> tournaments;
}