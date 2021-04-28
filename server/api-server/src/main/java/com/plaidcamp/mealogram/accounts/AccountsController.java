package com.plaidcamp.mealogram.accounts;

import org.springframework.stereotype.Controller;

@Controller
public class AccountsController {

    public TokenReponse login() {
        return new TokenReponse();
    }

}
