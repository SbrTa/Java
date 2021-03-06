package com.example.image;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Image {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;


	public Image(String name) {
		this.name = name;
	}
}
