package com.mustache.bbs5.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;
    private String password;
    private String emailAddress;

}
