package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ConsoleWriter implements LogWriter {
    public void writer(String text) {
        System.out.println("Console writer..." + text);
    }

}
