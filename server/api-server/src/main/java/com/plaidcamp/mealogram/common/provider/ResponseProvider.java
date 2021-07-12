package com.plaidcamp.mealogram.common.provider;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseProvider {

    public Map<String, Object> getResponse(Boolean success, Object res, Object err) {
        return new HashMap<String, Object>() {
            {
                put("success", success);
                put("response", res);
                put("error", err);
            }
        };
    }

}
