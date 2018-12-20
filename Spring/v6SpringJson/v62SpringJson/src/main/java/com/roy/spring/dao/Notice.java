/*
package com.roy.spring.dao;


import com.test.validation.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Notice {
    private int id;

    @Size(min = 5, max = 50, message = "Name must be between 5 to 50 characters")
    private String name;

    @NotNull
    @ValidEmail
    private String email;

    @Size(min = 5, max = 50, message = "Text must be between 5 to 250 characters")
    private String text;

    public Notice(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Notice(int id, String name, String email, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }
}
*/
