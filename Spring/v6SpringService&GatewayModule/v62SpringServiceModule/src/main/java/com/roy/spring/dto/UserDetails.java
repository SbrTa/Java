package com.roy.spring.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "userdetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "userName")
    private String userName;
    private String name;
    private String dob;
    private String blood;
    private String gender;
    private String city;
    private String contact;
    private String relation;
    private String bio;

    public UserDetails(String userName, String name, String dob, String blood, String gender, String city, String contact, String relation, String bio) {
        this.userName = userName;
        this.name = name;
        this.dob = dob;
        this.blood = blood;
        this.gender = gender;
        this.city = city;
        this.contact = contact;
        this.relation = relation;
        this.bio = bio;
    }

}
