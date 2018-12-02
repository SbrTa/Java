package org.roy.spring;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Cat {
    @Autowired
    @Value("12345")
    private int id;

    @Autowired
    @Value("Meaw")
    private String speech;

    public void speak(){
        System.out.println(id+ " " +speech);
    }

}
