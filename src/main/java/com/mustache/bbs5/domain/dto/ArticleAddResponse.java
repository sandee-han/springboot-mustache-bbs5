package com.mustache.bbs5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddResponse {
    public Long id;
    private String title;
    private String content;
}
