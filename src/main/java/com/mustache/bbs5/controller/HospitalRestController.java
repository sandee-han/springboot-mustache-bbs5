package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.dto.HospitalResponse;
import com.mustache.bbs5.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Long id) { // ResponseEntity도 DTO타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }
}