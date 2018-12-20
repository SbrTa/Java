package com.roy.spring.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "final")
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String time;
    @Column(name = "userName")
    private String userName;
    private String email;
    @Column(length = 5000)
    private String content;

    public UserPost(String time, String userName, String email, String content) {
        this.time = time;
        this.userName = userName;
        this.email = email;
        this.content = content;
    }
}
