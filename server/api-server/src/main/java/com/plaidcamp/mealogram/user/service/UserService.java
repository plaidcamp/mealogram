package com.plaidcamp.mealogram.user.service;

import com.plaidcamp.mealogram.common.constants.AuthConstant;
import com.plaidcamp.mealogram.common.error.exceptions.CustomException;
import com.plaidcamp.mealogram.common.error.exceptions.InvalidDataException;
import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;
import com.plaidcamp.mealogram.common.utils.DataCheckUtils;
import com.plaidcamp.mealogram.common.vo.ResponseVo;
import com.plaidcamp.mealogram.domain.user.UserMaster;
import com.plaidcamp.mealogram.domain.user.UserMasterRepository;
import com.plaidcamp.mealogram.user.dto.LoginDto;
import com.plaidcamp.mealogram.user.dto.RegistrationDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final DataCheckUtils dataUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMasterRepository userRepository;

    public ResponseEntity<ResponseVo> loginService(LoginDto user) throws CustomException {

        // 0. email regex check
        if(!dataUtils.emailRegexCheck(user.getEmail())) {
            throw new InvalidDataException("정상적인 이메일 형식이 아닙니다.");
        }

        // 1. find userData by email
        UserMaster member = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new InvalidDataException("가입되지 않은 E-MAIL 입니다."));

        logger.info("MEMBER : " + member.toString());

        // 2. password matching
        if(!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new InvalidDataException("잘못된 비밀번호입니다.");
        }

        // 3. Create JWT Token
        HttpHeaders header = new HttpHeaders();
        header.set(AuthConstant.AUTH_HEADER_A, jwtTokenProvider.createAccessToken(member));
        header.set(AuthConstant.AUTH_HEADER_R, jwtTokenProvider.createRefreshToken(member));
        return sendReturn(member, header);
    }

    /**
     * 회원가입 Service
     * @param user
     * @return
     * @throws CustomException
     */
    public ResponseEntity<ResponseVo> registerService(RegistrationDto user) throws CustomException {
        logger.info("param : " + user.toString());
        // 1. email 형식 확인
        if(!dataUtils.emailRegexCheck(user.getEmail())) {
            logger.info("Email Regex 맞지 않음");
            throw new InvalidDataException("Email Regex 맞지 않음");
        }

        // 2. userMaster create
        UserMaster userMaster = UserMaster.builder()
                                          .email(user.getEmail())
                                          .phone(user.getPhone())
                                          .password(user.getPassword())
                                          .administrate(AuthConstant.ROLE_USER)
                                          .build();
        logger.info("UserMaster 생성");

        // 3. userMaster save & get userKey
        String userKey = userRepository.save(userMaster).getId().toString();
        return sendReturn(userKey);
    }

    private ResponseEntity<ResponseVo> sendReturn(Object result) {
        return sendReturn(result, null);
    }

    private ResponseEntity<ResponseVo> sendReturn(Object result, HttpHeaders header) {
        header.setContentType(new MediaType("text", "application/json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                new ResponseVo.Builder()
                        .status(HttpStatus.OK)
                        .result(result)
                        .build(),
                header,
                HttpStatus.OK
        );
    }
}
