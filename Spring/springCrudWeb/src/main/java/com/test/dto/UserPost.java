package com.test.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPost {
    private int id;
    private String time;
    private String userName;
    private String email;
    private String content;

    public UserPost(int id, String time, String userName, String email, String content) {
        this.id = id;
        this.time = time;
        this.userName = userName;
        this.email = email;
        this.content = content;
    }
}
