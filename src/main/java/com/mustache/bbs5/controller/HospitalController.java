package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.Article;
import com.mustache.bbs5.domain.Hospital;
import com.mustache.bbs5.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/list")
    public String list(Model model) {
        List<Hospital> hospitalsList = hospitalRepository.findAll();
        model.addAttribute("hospitalsList", hospitalsList);
        return "hospital/hospitalslist";
    }

    @GetMapping("")
    public String index() {
        return "redirect:/hospitals/list";
    }

}
