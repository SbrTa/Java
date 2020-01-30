package com.example.image.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserChoice implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Integer talkativeLevel;
    private Integer findFaultLevel;
    private Integer thoroughJobLevel;
    private Integer depressionLevel;
}
