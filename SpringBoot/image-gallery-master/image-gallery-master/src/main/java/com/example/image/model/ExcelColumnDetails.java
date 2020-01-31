package com.example.image.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SbrTa
 * @since 1/31/2020  3:25 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExcelColumnDetails implements Serializable {
    private String parameter;
    private String header;
}