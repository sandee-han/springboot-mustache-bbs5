package com.mustache.bbs5.domain.dto;

import com.mustache.bbs5.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() {
        //Article의 경우 id가 generatedvalue임
        // Article에 builder추가해야됨
        Article article = Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
        return article;
    }
}
