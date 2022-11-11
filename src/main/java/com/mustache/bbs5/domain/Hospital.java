package com.mustache.bbs5.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals") // hospital이라는 table이 아님
@Getter
@NoArgsConstructor
public class Hospital {
    @Id // GeneratedValue 안씀
    private Integer id; // Long -> BigInteger
    private String hospital_name;
    private String full_address;

    public Hospital(String hospital_name, String full_address) {
        this.hospital_name = hospital_name;
        this.full_address = full_address;
    }
}
