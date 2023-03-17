package com.mustache.bbs5.repository;

import com.mustache.bbs5.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void name() {
        Optional<Hospital> hospital = hospitalRepository.findById(1l);
        Hospital hp = hospital.get();
        System.out.println(hp.getId());
        assertEquals(1, hp.getId());
        assertEquals("효치과의원", hp.getName());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hp.getAddress());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");

        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital :
                hospitals) {
            System.out.println(hospital.getName());
        }
    }

    @Test
    @DisplayName("광진구를 담고있느냐.")
    void findByAddressIsContaining() {
        List<Hospital> hospitals = hospitalRepository.findByAddressIsContaining("광진구");
        for (var hospital :
                hospitals) {
            System.out.println(hospital.getAddress());
        }
    }

    @Test
    @DisplayName("광진구 + 보건소, 보건지소, 보건진료소를 갖는 병원들 출력")
    void findByAddressAndTypeName() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");

        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        hospitals = hospitalRepository.findByAddressIsContaining("광진구");
        for (var hospital :
                hospitals) {
            System.out.println(hospital.getName());
        }
    }

    @Test
    @DisplayName("병상 수가 10개 이상, 20개 미만인 병원")
    void findByNumbersOfBedsBetween() {
        List<Hospital> hospitals = hospitalRepository.findByBedsBetween(10, 19);
        for (var hospital :
                hospitals) {
            System.out.printf("%s: %d\n", hospital.getName(), hospital.getBeds());
        }
    }

}
