package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.entity.Hospital;
import com.mustache.bbs5.domain.entity.User;
import com.mustache.bbs5.domain.entity.Visit;
import com.mustache.bbs5.domain.dto.VisitRequest;
import com.mustache.bbs5.exception.ErrorCode;
import com.mustache.bbs5.exception.HospitalReviewAppException;
import com.mustache.bbs5.repository.HospitalRepository;
import com.mustache.bbs5.repository.UserRepository;
import com.mustache.bbs5.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;



    public void create(VisitRequest dto, String userName) {
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new HospitalReviewAppException
                                        (ErrorCode.NOT_FOUND, String.format("hospitalId: %s가 없습니다.", dto.getHospitalId())));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException
                                        (ErrorCode.USER_NOT_FOUND, String.format("%s user가 없습니다.", userName)));

        Visit savedVisit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(dto.getDisease())
                .build();
        visitRepository.save(savedVisit);
    }
}
