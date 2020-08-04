package com.sbrta.movieinfoservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SbrTa
 * @since 8/3/2020  8:40 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSummary implements Serializable {
    private String title;
    private String overview;
}
