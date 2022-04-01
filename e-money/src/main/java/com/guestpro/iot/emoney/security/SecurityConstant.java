package com.guestpro.iot.emoney.security;

public class SecurityConstant {
    public static final String AUTH_LOGIN_URL = "/auth/token";
    public static final String REGISTER_USER_URL = "/api/user/register";
    public static final String JWT_SECRET = "+MbQeThWmZq4t7w!z%C*F-JaNcRfUjXn2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "gp-cloud-pos";
    public static final String TOKEN_AUDIENCE = "gp-e-money";
    public static final long TOKEN_EXPIRED = 86400000;

}
