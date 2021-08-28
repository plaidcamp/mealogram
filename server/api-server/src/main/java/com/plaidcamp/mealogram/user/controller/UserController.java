package com.plaidcamp.mealogram.user.controller;

import com.plaidcamp.mealogram.common.error.exceptions.CustomException;
import com.plaidcamp.mealogram.common.vo.ResponseVo;
import com.plaidcamp.mealogram.user.dto.LoginDto;
import com.plaidcamp.mealogram.user.dto.RegistrationDto;
import com.plaidcamp.mealogram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<ResponseVo> join(@RequestBody RegistrationDto user) throws CustomException {
        System.out.println("param : " + user.toString());
        return userService.registerService(user);
    }

    // 로그인[
    @PostMapping("/login")
    public ResponseEntity<ResponseVo> login(@RequestBody LoginDto user) throws CustomException {
        return userService.loginService(user);
    }
}