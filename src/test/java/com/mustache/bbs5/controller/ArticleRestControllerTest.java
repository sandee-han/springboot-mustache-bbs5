package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.dto.ArticleResponse;
import com.mustache.bbs5.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("json형태로 response가 잘 옵니까?? 예??")
    void articleJsonResponse() throws Exception {
        ArticleResponse articleResponse = ArticleResponse.builder()
                .id(1L)
                .title("test")
                .content("test content").build();

        given(articleService.getArticle(1L)).willReturn(articleResponse);

        Long articleId = 1L;
        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("test"))
                .andDo(print());

        verify(articleService).getArticle(articleId);
    }
}