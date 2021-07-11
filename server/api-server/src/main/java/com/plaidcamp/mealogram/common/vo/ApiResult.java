package com.plaidcamp.mealogram.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ApiResult {

    private Object data;

    private Object error;

    private HttpStatus stats;

}
