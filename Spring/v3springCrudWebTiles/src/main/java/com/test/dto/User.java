package com.test.dto;


import com.test.validation.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class User {
    private int id;

    @Size(min = 5, max = 50, message = "Please Enter your full name.")
    private String name;

    @NotNull
    @ValidEmail
    private String email;

    @Size(min = 5, message = "Username must be at least 5 characters.")
    private String userName;
    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;
    private String role;
}
