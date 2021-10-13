package com.example.qlbhbe.util;

public class Constants {
    public static final String SUCCESS = "SUCCESS";
    public static final String DATETIME_PARAM_FORMAT = "yyyyMMddHHmmss";
    public static final String API = "/api";
    public static final String LOGIN_URL = API + "/login";
    public static final String LOGIN_OTP = API + "/otpLogin";
    public static final String REFRESH_TOKEN_URL = API + "/refreshToken";
    public static final String TEMP_FOLDER = "temp";
    public static final String PUBLIC_FOLDER = "public";

    public static final String[] PNG_JPG = new String[]{"image/jpeg", "image/png"};

    public static final String DESCRIPTION = "description";
    public static final String CODE_UNAUTHORIZED = "UNAUTHORIZED";
    public static final String CODE_REFRESH_TOKEN_EXPIRED = "REFRESH_TOKEN_EXPIRED";
    public static final String CODE = "code";
    public static final String CLAIM_SCOPE = "scope";
    public static final String CLAIM_SCOPE_ACCESS = "access";
    public static final String CLAIM_SCOPE_REFRESH_TOKEN = "refresh";
    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_AUTH_PROVIDER = "authProvider";

    public static final String AC_API_Key = "AC_API_Key";
    public static final String Authorization = "Authorization";
    public static final String AcceptLanguage = "Accept-Language";

    // Equals to Integer.MAX_VALUE - 1
    public static final Integer ALPHA_CLOUD_USER_ID = 2147483646;
}
