package com.plaidcamp.mealogram.apiServer.mealogram.controller;

import com.plaidcamp.mealogram.apiServer.mealogram.service.MealogramService;
import com.plaidcamp.mealogram.apiServer.mealogram.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class MealogramController {

    @Autowired
    MealogramService mealogramService;

    @GetMapping("/")
    public String CheckStatus() {
        return "CHECK";
    }

    @GetMapping("/{user}")
    public String showPostAll(@PathVariable("user") String userid) {

        String returns = new String("Show All");

        return returns;
    }

    @GetMapping("/{user}/{id}")
    public String showPost(@PathVariable("user") String userid,
                       @PathVariable("id")   String id) {

        String returns = new String("Show Post");

        return returns;
    }

    @PostMapping("/")
    public int insertPost(@RequestBody PostVo postVo) {

        int result = -1;


        return result;
    }
}
