package com.plaidcamp.mealogram.common.interceptor;

import com.plaidcamp.mealogram.common.constants.AuthConstant;
import com.plaidcamp.mealogram.common.error.exceptions.TokenException;
import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthCheckInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    private final JwtTokenProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Interceptor Entered");

        // request header로부터 token 가져오기
        String[] token = jwtProvider.resolveToken(request);
        if(StringUtils.hasText(token[0])) { // access token이 존재한다면
            if(jwtProvider.validateToken(AuthConstant.ACCESS_TOKEN, token[0])) {

            }
        } else { // access token이 없다면
            throw new TokenException("AccessToken is null"); // 나는 exception 던져주고, 앞단에서 로그인으로 redirect
        }

        return false;
    }
}
