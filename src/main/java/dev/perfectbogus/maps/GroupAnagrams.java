package dev.perfectbogus.maps;

import java.util.*;

public class GroupAnagrams {

    public static Map<String, List<String>> group(String[] words) {
        if (words == null) throw new IllegalArgumentException("words cannot be null");
        if (words.length == 0) return Collections.emptyMap();

        Map<String, List<String>> groupedMap = new HashMap<>();

        for (String word : words) {
            if (word == null) throw new IllegalArgumentException("word cannot be null");

            char[] tempArray = word.toCharArray();
            Arrays.sort(tempArray);
            final String sortedKey = new String(tempArray);
            groupedMap.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(word);
        }

        return groupedMap;
    }
}
