package com.mustache.bbs5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class HospitalResponse {
    private Integer id;
    private String address;
    private String name;
    private Integer patientRoomCount;
    private Integer beds;
    private String businessTypeName;
    private Float totalAreaSize;
}