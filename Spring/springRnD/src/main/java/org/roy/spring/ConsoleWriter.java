package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsoleWriter implements LogWriter {
    public void writer(String text) {
        System.out.println("Console writer..." + text);
    }

}
