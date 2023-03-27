package com.mustache.bbs5.configuration;

import com.mustache.bbs5.domain.entity.User;
import com.mustache.bbs5.service.UserService;
import com.mustache.bbs5.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Header에서 authorization 꺼낸다. token 을 꺼냄
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        // authentication이 잘 꺼내졌는지 로그로 확인
        log.info("authorization : {}", authorization);

        // token 안꺼내면 block
        // bearer 로 시작하는지
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("Authorization 을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String token;

        // Token 꺼내기
        // authorization에서 꺼낸 것중 첫 번째
        try {
            token = authorization.split(" ")[1];
        } catch (Exception e) {
            log.error("token 추출에 실패했습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token Expired 되었는지 여부
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // UserName Token에서 꺼내기
        String userName = JwtUtil.getUserName(token, secretKey);
        log.info("userName: {}" , userName);

        User user = userService.getUserByUserName(userName);

        // 문 열어주기(권한 부여), Role 바인딩
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), null, List.of(new SimpleGrantedAuthority(user.getRole().name())));

        // Detail을 넣어준다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여
        filterChain.doFilter(request, response);
    }
}
