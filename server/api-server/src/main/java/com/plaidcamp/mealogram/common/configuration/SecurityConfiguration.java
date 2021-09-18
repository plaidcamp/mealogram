package com.plaidcamp.mealogram.common.configuration;

<<<<<<< HEAD
import com.plaidcamp.mealogram.common.constants.AuthConstant;
=======
import com.plaidcamp.mealogram.common.constants.AuthUtils;
>>>>>>> be0a44ba333991f0c0cc7429f0621b4baf396db2
import com.plaidcamp.mealogram.common.filter.JwtAuthenticationFilter;
import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;
import com.plaidcamp.mealogram.user.service.CustomUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
=======
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
>>>>>>> be0a44ba333991f0c0cc7429f0621b4baf396db2
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.http.SessionCreationPolicy;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
>>>>>>> be0a44ba333991f0c0cc7429f0621b4baf396db2
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Value("${profile}")
    private String profile;
    private CustomUserDetailService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 암호화에 필요한 PasswordEncoder를 Bean에 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

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
<<<<<<< HEAD
                    .antMatchers("/admin/**").hasRole(AuthConstant.ROLE_ADMIN)
                    .antMatchers("/account/**").hasRole(AuthConstant.ROLE_USER)
                    .antMatchers("/post/**").hasRole(AuthConstant.ROLE_USER)
=======
                    .antMatchers("/admin/**").hasRole(AuthUtils.ROLE_ADMIN)
                    .antMatchers("/account/**").hasRole(AuthUtils.ROLE_USER)
                    .antMatchers("/post/**").hasRole(AuthUtils.ROLE_USER)
>>>>>>> be0a44ba333991f0c0cc7429f0621b4baf396db2
                    .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
                    .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);
            // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
        }
    }
}