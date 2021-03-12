package com.example.consumer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author SbrTa
 * @since 3/12/21  11:57 AM
 */

@Getter
@Setter
@ToString
public class Person implements Serializable {
    private String name;
    private int age;
    private String gender;
}
