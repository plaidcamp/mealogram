package com.plaidcamp.mealogram.common.filter;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;

@RequiredArgsConstructor
@Order(1) // filter 순서.
public class JwtAuthenticationFilter extends GenericFilterBean {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                            throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}