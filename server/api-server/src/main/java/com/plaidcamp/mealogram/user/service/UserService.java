package com.plaidcamp.mealogram.user.service;

import com.plaidcamp.mealogram.domain.user.UserMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMasterRepository userRepository;

    public String checkLogin(Map<String, String> user) {
        // 1. check email


        return null;
    }

}
