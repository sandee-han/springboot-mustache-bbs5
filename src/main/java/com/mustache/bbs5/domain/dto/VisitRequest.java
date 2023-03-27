package com.mustache.bbs5.domain.dto;

import com.mustache.bbs5.domain.entity.Hospital;
import com.mustache.bbs5.domain.entity.User;
import com.mustache.bbs5.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class VisitRequest {
    private Long hospitalId;
    private String userName;
    private String disease;

    public Visit toEntity() {
        Visit visit = Visit.builder()
                .hospital(Hospital.builder().build())
                .user(User.builder().build())
                .disease(this.disease)
                .build();
        return visit;
    }
}
