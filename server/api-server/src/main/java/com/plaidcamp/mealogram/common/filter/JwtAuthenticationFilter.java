package com.plaidcamp.mealogram.common.filter;

import com.plaidcamp.mealogram.common.constants.Token;
import lombok.RequiredArgsConstructor;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;

@RequiredArgsConstructor
@Order(1) // filter 순서.
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                            throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 유효한 access 토큰인지 확인합니다.
        if (token != null && jwtTokenProvider.validateToken(Token.ACCESS, token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(Token.ACCESS, token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // access token이 없을 시 재발급 필요
        else if("".equals(token)) {
            // TODO : 재발급 전 검증해야 할 사항은 무엇인가
            // refresh token 확인

        }
        chain.doFilter(request, response);
    }
}