package com.example.image;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Data
@NoArgsConstructor
@Entity
public class SiteUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    private boolean enabled;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Image> imageList;

    public SiteUser(String name, String password, boolean enabled) {
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

}
