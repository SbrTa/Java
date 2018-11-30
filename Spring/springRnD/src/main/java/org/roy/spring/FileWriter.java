package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileWriter implements LogWriter {
    public void writer(String text){
        System.out.println("File writer..."+text);
    }
}
