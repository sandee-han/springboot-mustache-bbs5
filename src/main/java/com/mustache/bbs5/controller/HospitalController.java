package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.Article;
import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/hospitals")
public class HospitalController {
    private HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/")
    public String list(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        model.addAttribute("hospitals", getHospitalList(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/hospitalslist";
    }

    @Transactional
    public Page<Hospital> getHospitalList(Pageable pageable) {
        return hospitalRepository.findAll(pageable);
    }

    @GetMapping("")
    public String index() {
        return "redirect:/hospitals/";
    }

}
