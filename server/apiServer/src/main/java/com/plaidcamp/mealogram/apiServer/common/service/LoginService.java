package com.plaidcamp.mealogram.apiServer.common.service;

import com.plaidcamp.mealogram.apiServer.common.repository.LoginRepository;
import com.plaidcamp.mealogram.apiServer.common.vo.LoginVo;

import com.plaidcamp.mealogram.apiServer.error.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public LoginVo getUserData(String primaryKey) {
        LoginVo loginData =  loginRepository.findById(primaryKey).orElse(null);
//                .orElseThrow(() -> {
//            throw new NotFoundException("User is none");
//        });

        try {
            return loginData;
        } catch(NotFoundException e) {
            e.printStackTrace();

            return null;
        }
    }

}
