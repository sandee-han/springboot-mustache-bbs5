package com.mustache.bbs5.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_visit")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id") //foreign key의 컬럼명
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id") //foreign key의 컬럼명
    private User user;

    private String disease;
}
