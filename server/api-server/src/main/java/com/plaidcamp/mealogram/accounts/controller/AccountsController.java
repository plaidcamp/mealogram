package com.plaidcamp.mealogram.accounts.controller;

import com.plaidcamp.mealogram.accounts.TokenReponse;
import com.plaidcamp.mealogram.domain.user.UserMaster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountsController {

    @PostMapping("/login")
    public String AccountLogin(@RequestBody UserMaster userMaster) {


        return "loginPage";
    }

    @PostMapping("/register")
    public String AccountRegister(@RequestBody UserMaster userMaster) {

        return "registerPage";
    }

}
