package com.example.image.model;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class MovieToRate implements Serializable{
    private Long id;
    private String name;
    private String logo;
    private Long ratingId;
    private Long ratingSystem;
    private Long serial;
}
