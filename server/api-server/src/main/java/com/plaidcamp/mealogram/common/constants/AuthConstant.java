package com.plaidcamp.mealogram.common.constants;

public interface AuthConstant {
    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ROLE_USER = "USER";

    public static final String AUTH_HEADER_A = "x-auth-access";
    public static final String AUTH_HEADER_R = "x-auth-refresh";


    // token constant value
    public static final String ACCESS_TOKEN = "ACCESS";

    public static final int ACCESS_TOKEN_DURATION = 2; // 2시간
    public static final int REFRESH_TOKEN_DURATION = 14 ; // 2주일

    public static final String REFRESH_TOKEN = "REFRESH";
}
