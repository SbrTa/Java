package com.roy.spring.dto;


import com.roy.spring.validation.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 5, max = 50, message = "Please Enter your full name.")
    private String name;

    @NotNull
    @ValidEmail
    private String email;

    @Column(name = "userName")
    @Size(min = 5, message = "Username must be at least 5 characters.")
    private String userName;
    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;
    private String role;
}
