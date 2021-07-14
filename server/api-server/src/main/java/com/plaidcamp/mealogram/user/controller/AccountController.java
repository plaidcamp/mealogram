package com.plaidcamp.mealogram.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/{id}")
    public String getAccountList() {
        return null;
    }

    @GetMapping("/{id}/{account}")
    public String getAccount(@PathVariable String id, @PathVariable String account) {
        return null;
    }
}