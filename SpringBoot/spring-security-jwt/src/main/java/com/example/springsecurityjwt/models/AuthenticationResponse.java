package com.example.springsecurityjwt.models;

/**
 * @author SbrTa
 * @since 3/4/2021  12:02 AM
 */

public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
