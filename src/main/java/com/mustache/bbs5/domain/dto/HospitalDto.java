package com.mustache.bbs5.domain.dto;

public class HospitalDto {
    private Long id;
    private String hospital_name;
    private String full_address;

    public HospitalDto(Long id, String hospital_name, String full_address) {
        this.id = id;
        this.hospital_name = hospital_name;
        this.full_address = full_address;
    }

}
