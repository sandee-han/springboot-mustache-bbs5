package com.mustache.bbs5.domain.entity;

import com.mustache.bbs5.domain.dto.HospitalResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Table(name = "t_nation_wide_hospitals")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "open_service_name")
    private String openServiceName; //개방서비스명

    @Column(name = "open_local_government_code") //개방자치단체코드
    private int openLocalGovernmentCode;

    @Column(name = "management_number") //관리번호
    private String managementNumber;

    @Column(name = "license_date")
    private LocalDateTime LicenseDate; //인허가일자

    @Column(name = "business_status")
    private int businessStatus; //1: 영업/정상 2: 휴업 3: 폐업 4: 취소/말소영업상태구분

    @Column(name = "business_status_code")
    private int businessStatusCode; //영업상태코드 2: 휴업 3: 폐업 13: 영업중

    @Column(name = "phone")
    private String phone; // 소재지전화

    @Column(name = "full_address")
    private String fullAddress; //소재지전체주소

    @Column(name = "road_name_address")
    private String roadNameAddress; //도로명전체주소

    @Column(name = "hospital_name")
    private String hospitalName; //사업장명(병원이름)

    @Column(name = "business_type_name")
    private String businessTypeName; //업태구분명

    @Column(name = "healthcare_provider_cnt")
    private int healthcareProviderCount; //의료인수

    @Column(name = "patient_room_cnt")
    private int patientRoomCount; //입원실수

    @Column(name = "total_number_of_beds")
    private int totalNumberOfBeds; //병상 수

    @Column(name = "total_area_size")
    private float totalAreaSize; //총면적

    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분
    public static HospitalResponse of(Hospital hospital) {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(hospital.getId())
                .hospitalName(hospital.getHospitalName())
                .patientRoomCount(hospital.getPatientRoomCount())
                .totalNumberOfBeds(hospital.getTotalNumberOfBeds())
                .businessStatusName(setBusinessStatusName(hospital.getBusinessStatusCode()))
                .businessTypeName(hospital.getBusinessTypeName())
                .build();

        return hospitalResponse;
    }

    private static String setBusinessStatusName(int businessStatusCode) {
        if (businessStatusCode == 13) {
            return "영업중";
        } else if (businessStatusCode == 3) {
            return "폐업";
        } else {
            return String.valueOf(businessStatusCode);
        }
    }

}
