package com.example.image.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum RatingSystem {
    COLOR_CIRCLE(1,"Color Circle"),
    COLOR_STAR(2,"Color Star"),
    COLOR_EMO(3,"Color Emoticon"),
    GRADIENT(4,"Gradient"),
    BW_CIRCLE(5,"BW Circle"),
    BW_EMO(6,"BW Emoticon"),
    BW_STAR(7,"BW Star");
    int ratingSystem;
    String ratingSystemName;

    RatingSystem() {
    }

    RatingSystem(int ratingSystem, String ratingSystemName) {
        this.ratingSystem = ratingSystem;
        this.ratingSystemName = ratingSystemName;
    }
}
