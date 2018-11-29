package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient {

    private int id;
    private String name;
    private Address address;

    public void speak(){
        System.out.println("Help !  Help !");
    }
}
