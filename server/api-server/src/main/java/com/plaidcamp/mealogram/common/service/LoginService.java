package com.plaidcamp.mealogram.common.service;

import com.plaidcamp.mealogram.common.error.exceptions.NotFoundException;
import com.plaidcamp.mealogram.common.vo.LoginVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public LoginVo getUserData(String primaryKey) {
        LoginVo loginData = loginRepository.findById(primaryKey).orElse(null);
        // .orElseThrow(() -> {
        // throw new NotFoundException("User is none");
        // });

        try {
            return loginData;
        } catch (NotFoundException e) {
            e.printStackTrace();

            return null;
        }
    }

}
