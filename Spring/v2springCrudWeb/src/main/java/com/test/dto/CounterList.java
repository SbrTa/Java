package com.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CounterList {
    private int post;
    private List<Integer> likerList;
    private List<Integer> dislikerList;

    public CounterList(int post, List<Integer> likerList, List<Integer> dislikerList) {
        this.post = post;
        this.likerList = likerList;
        this.dislikerList = dislikerList;
    }
}
