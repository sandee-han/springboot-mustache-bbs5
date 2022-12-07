package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.dto.HospitalResponse;
import com.mustache.bbs5.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean // @Autowired 아님!!! -> HospitalService는 테스트를 위해 가짜 객체를 쓰겠다는 뜻
    HospitalService hospitalService; // -> 가짜 객체를 쓰면 좋은점 : DB와 상관없이 테스트 가능

    @Test
    @DisplayName("Json형태로 Response가 잘 오는지") // 비즈니스로직 Controller만 검증 (Service를 점증하지 않음)
    void jsonResponse() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                                        .id(2321)
                                        .address("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                                        .name("노소아청소년과의원")
                                        .patientRoomCount(0)
                                        .beds(0)
                                        .businessTypeName("의원")
                                        .totalAreaSize(0.0f)
                                        .businessStatusName("영업중")
                                        .build();

        given(hospitalService.getHospital(2321)).willReturn(hospitalResponse);

        int hospitalId = 2321;

        // 앞에서 Autowired한 mockMvc
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("노소아청소년과의원"))
                .andDo(print());

        verify(hospitalService).getHospital(hospitalId);

    }
}