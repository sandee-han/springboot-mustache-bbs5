package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByAddressIsContaining(String address);

    List<Hospital> findByBedsBetween(int min, int max);

    Page<Hospital> findByAddressContaining(String keyword, Pageable pageable);
}
