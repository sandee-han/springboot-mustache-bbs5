package com.mustache.bbs5.domain;

import com.mustache.bbs5.domain.dto.HospitalResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals") // hospital이라는 table이 아님
@Getter
public class Hospital {
    @Id // GeneratedValue 안씀
    private Integer id; // Long -> BigInteger

    @Column(name = "hospital_name")
    private String name;

    @Column(name = "road_name_address")
    private String address;

    private Integer patientRoomCount;

    @Column(name = "total_number_of_beds")
    private Integer beds;

    private String businessTypeName;
    private Float totalAreaSize;


    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getAddress(), hospital.getName(),
                                    hospital.getPatientRoomCount(), hospital.getBeds(),
                                    hospital.getBusinessTypeName(), hospital.getTotalAreaSize());
    }

}
