package com.demia.sdk.core;

public class TokenHolder {

    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static final String getToken() {
        return tokenHolder.get();
    }

    public static final void setToken(String token) {
        tokenHolder.set(token);
    }

}
