package com.plaidcamp.mealogram.post.controller;

import com.plaidcamp.mealogram.domain.post.Post;
import com.plaidcamp.mealogram.post.service.MealogramService;

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
    public String showPost(@PathVariable("user") String userid, @PathVariable("id") String id) {

        String returns = new String("Show Post");

        return returns;
    }

    @PostMapping("/")
    public int insertPost(@RequestBody Post post) {

        int result = -1;

        return result;
    }
}
