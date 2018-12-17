package com.roy.spring.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int post;
    private String liker;
    private String disliker;

    public Counter(int post, String liker, String disliker) {
        this.post = post;
        this.liker = liker;
        this.disliker = disliker;
    }
}
