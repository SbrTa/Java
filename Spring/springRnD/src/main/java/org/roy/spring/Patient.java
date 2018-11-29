package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Patient {

    private int id;
    private String name;
//    private Address address;
    private List<EmergencyContact> emergencyContactNames;
}
