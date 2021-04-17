package com.plaidcamp.mealogram.apiServer.common.controller;

import com.plaidcamp.mealogram.apiServer.common.service.LoginService;
import com.plaidcamp.mealogram.apiServer.common.vo.LoginVo;
import com.plaidcamp.mealogram.apiServer.error.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/2")
    public List<String>  root_test() throws Exception{
        List<String> returns = new ArrayList<String>();

        returns.add("hello");
        returns.add("spring");
        returns.add("boot");

        return returns;
    }

    @GetMapping("/{user}")
    public @ResponseBody
    List<LoginVo> demo_test(@PathVariable("user") String userid) throws NotFoundException {
        List<LoginVo> returns = new ArrayList<LoginVo>();

        returns.add(loginService.getUserData(userid));

        return returns;
    }

}
