package com.roy.spring.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Json {
    private String name;
    private String email;

    public Json(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
