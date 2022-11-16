package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.Article;
import com.mustache.bbs5.domain.dto.ArticleResponse;
import com.mustache.bbs5.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        Article article = optionalArticle.get();
        ArticleResponse articleResponse = Article.of(article);
        return articleResponse;
    }
}
