package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.domain.dto.HospitalResponse;
import com.mustache.bbs5.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {
    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("")
    void getHospitalTest() {
        Hospital hospital1 = Hospital.builder()
                .id(4)
                .businessStatusCode(13)
                .build();

        Hospital hospital2 = Hospital.builder()
                .id(41)
                .businessStatusCode(3)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital1));

        HospitalResponse hospitalResponse1 = hospitalService.getHospital(4);
        assertEquals("영업중", hospitalResponse1.getBusinessStatusName());

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital2));

        HospitalResponse hospitalResponse2 = hospitalService.getHospital(41);
        assertEquals("폐업", hospitalResponse2.getBusinessStatusName());
    }


}