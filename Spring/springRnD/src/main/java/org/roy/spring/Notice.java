package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notice {
    private int id;
    private String name;
    private String email;
    private String text;

    public Notice(String name, String email, String text) {
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Notice(int id, String name, String email, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }
}
