package com.plaidcamp.mealogram.common.configuration;

import com.plaidcamp.mealogram.common.filter.JwtAuthenticationFilter;
import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Value("${profile}")
    private String profile;

    private final JwtTokenProvider jwtTokenProvider;

    // 암호화에 필요한 PasswordEncoder를 Bean에 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticationManager를 Bean에 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("profile : " + profile);

        if("DEV".equals(profile.toUpperCase()) || "LOCAL".equals(profile.toUpperCase())) {
            http.httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                    .csrf().disable() // Cross Site Request Forgery 보안 토큰 disable처리. (나중에 referer check 필요)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                    .and()
                    .authorizeRequests() // 요청에 대한 사용권한 체크
                    .anyRequest().permitAll() //개발환경에서는 Role체크 없음
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);

        } else {
            http.httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                    .csrf().disable() // Cross Site Request Forgery 보안 토큰 disable처리. (나중에 referer check 필요)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                    .and()
                    .authorizeRequests() // 요청에 대한 사용권한 체크
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/account/**").hasRole("USER")
                    .antMatchers("/post/**").hasRole("USER")
                    .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);
            // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
        }
    }
}