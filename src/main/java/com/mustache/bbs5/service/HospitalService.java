package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.entity.Hospital;
import com.mustache.bbs5.domain.dto.HospitalResponse;
import com.mustache.bbs5.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public Slice<Hospital> getHospitalList(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id); // Entity
        Hospital hospital = optionalHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital); // DTO
        return hospitalResponse;
    }

}
