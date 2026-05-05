package dev.perfectbogus.maps;

import java.util.HashMap;
import java.util.Map;

public class WordFrequency {

    public static Map<String, Integer> calculate(String str) {
        if (str == null) throw new IllegalArgumentException("Input cannot be null");
        if (str.isBlank()) return new HashMap<>();

        Map<String, Integer> freq = new HashMap<>();

        for (String word : str.trim().split("\\s+")) {
            freq.merge(word, 1, Integer::sum);
        }

        return freq;
    }
}
