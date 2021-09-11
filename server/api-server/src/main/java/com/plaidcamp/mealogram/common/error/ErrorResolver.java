package com.plaidcamp.mealogram.common.error;

import com.plaidcamp.mealogram.common.error.exceptions.CustomException;
import com.plaidcamp.mealogram.common.error.exceptions.InvalidDataException;
import com.plaidcamp.mealogram.common.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ErrorResolver {

    @ExceptionHandler({
            InvalidDataException.class
    })
    public ResponseEntity<ResponseVo> throwInvalidException(final InvalidDataException cx) {
        cx.printException();

        return new ResponseEntity<>(
                new ResponseVo.Builder()
                        .status(cx.getStatus())
                        .result(cx.getErrMsg())
                        .build(),
                cx.getStatus()
        );
    }


    /**
     * private : ResponseEntity 생성 모듈 private으로 따로 작성
     * @param ex
     * @return
     */
    private ResponseEntity<ResponseVo> sendReturn(CustomException ex, HttpStatus status) {
        return new ResponseEntity<>(
                new ResponseVo.Builder()
                        .status(ex.getStatus())
                        .result(ex.getErrMsg())
                        .build(),
                status
        );
    }
}
