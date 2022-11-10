package com.mustache.bbs5.domain.dto;

import com.mustache.bbs5.domain.Article;
import lombok.Getter;

@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
