package com.app.users.Security;

public class SecurityConstatns {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String REGISTER_URL = "/api/users/register";
}
