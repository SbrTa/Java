package com.test.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Counter {
    private int id;
    private int post;
    private String liker;
    private String disliker;

    public Counter(int id, int post, String liker, String disliker) {
        this.id = id;
        this.post = post;
        this.liker = liker;
        this.disliker = disliker;
    }
}
