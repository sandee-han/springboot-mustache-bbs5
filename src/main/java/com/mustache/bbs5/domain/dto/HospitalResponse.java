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

    public HospitalResponse(Integer id, String address, String name, Integer patientRoomCount, Integer beds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.patientRoomCount = patientRoomCount;
        this.beds = beds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }

    private String businessStatusName;

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

}