package com.example.image.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long movieId;
    private Long ratingSystem;
    private String ratingSystemName;
    private Long rating;
    private Boolean isRated=false;
    private Long serial;
}
