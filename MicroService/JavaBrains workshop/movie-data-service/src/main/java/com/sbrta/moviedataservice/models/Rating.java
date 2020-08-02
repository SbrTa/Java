package com.sbrta.moviedataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SbrTa
 * @since 8/3/2020  12:30 AM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating implements Serializable {
    private String movieId;
    private int rating;
}
