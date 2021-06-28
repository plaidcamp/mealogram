package com.plaidcamp.mealogram.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import com.plaidcamp.mealogram.common.security.JwtTokenProvider;
import com.plaidcamp.mealogram.domain.user.UserMaster;
import com.plaidcamp.mealogram.domain.user.UserMasterRepository;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMasterRepository userRepository;

    // 회원가입
    @PostMapping("/register")
    public UUID join(@RequestBody Map<String, String> user) {
        return userRepository.save(
                UserMaster.builder().email(user.get("email")).password(passwordEncoder.encode(user.get("password")))
                        .roles(Collections.singletonList("ROLE_USER")).build())
                .getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        UserMaster member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}