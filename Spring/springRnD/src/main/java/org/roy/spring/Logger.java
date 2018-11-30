package org.roy.spring;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class Logger {
    @Autowired
    private FileWriter fileWriter;
    @Autowired
    private ConsoleWriter consoleWriter;

    public void writeFile(String text){
        fileWriter.writer(text);
    }
    public void writeConsole(String text){
        consoleWriter.writer(text);
    }
}
