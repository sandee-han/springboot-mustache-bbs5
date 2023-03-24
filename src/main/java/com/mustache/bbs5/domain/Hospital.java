package com.mustache.bbs5.domain;

import com.mustache.bbs5.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_nation_wide_hospitals")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id // GeneratedValue 안씀
    private Long id; // Long -> BigInteger

    @Column(name = "hospital_name")
    private String name;

    @Column(name = "road_name_address")
    private String address;

    private Integer patientRoomCount;

    @Column(name = "total_number_of_beds")
    private Integer beds;

    private String businessTypeName;
    private Float totalAreaSize;
    private Integer businessStatusCode;

    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getAddress(), hospital.getName(),
                                    hospital.getPatientRoomCount(), hospital.getBeds(),
                                    hospital.getBusinessTypeName(), hospital.getTotalAreaSize());
    }

}
