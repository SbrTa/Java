package org.roy.spring;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomSpeech {
    private static String[] texts={
            "text 1",
            "text 22",
            "text 333",
            null
    };

    public String getText() {
        Random random = new Random();
        return texts[random.nextInt(texts.length)];
    }
}
