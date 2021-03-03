package com.example.springsecurityjwt.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SbrTa
 * @since 3/4/2021  12:00 AM
 */

@Getter
@Setter
public class AuthenticationRequest {
    private String username;
    private String password;
}
