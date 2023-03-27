package com.mustache.bbs5.domain.dto;

import com.mustache.bbs5.domain.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    private String author;
    public ArticleDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Article toEntity() {
        return new Article(id, title, content, author);
    }
}
