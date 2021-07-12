package com.plaidcamp.mealogram.auth.controller;

import com.plaidcamp.mealogram.auth.service.AuthService;
import com.plaidcamp.mealogram.common.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/token/access")
    public ResponseEntity<ApiResult> checkAccessToken() {
        return authService.checkAccessToken("");
    }
}
