package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.dto.VisitRequest;
import com.mustache.bbs5.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisit(@RequestBody VisitRequest dto) {
        visitService.create(dto, dto.getUserName());
        return ResponseEntity.ok().body("방문 기록이 등록 되었습니다.");
    }
}
