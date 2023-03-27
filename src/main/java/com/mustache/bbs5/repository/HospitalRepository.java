package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressIsContaining(String roadNameAddress);

    List<Hospital> findByTotalNumberOfBedsBetween(int min, int max);

    Page<Hospital> findByRoadNameAddressContaining(String keyword, Pageable pageable);
}
