package com.example.producer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author SbrTa
 * @since 3/11/21  10:52 PM
 */

@Getter
@Setter
@ToString
public class Person implements Serializable {
    private String name;
    private int age;
    private String gender;
}
