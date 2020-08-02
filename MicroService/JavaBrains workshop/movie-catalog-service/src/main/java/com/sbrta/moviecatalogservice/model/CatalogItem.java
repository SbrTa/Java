package com.sbrta.moviecatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SbrTa
 * @since 8/2/2020  4:55 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem implements Serializable {
    private String name;
    private String desc;
    private int rating;
}
