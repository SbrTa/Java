package com.sbrta.moviecatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author SbrTa
 * @since 8/3/2020  1:33 AM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRating implements Serializable {
    private String userId;
    private List<Rating> ratings;
}
