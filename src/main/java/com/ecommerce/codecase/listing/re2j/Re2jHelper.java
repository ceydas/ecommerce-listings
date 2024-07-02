package com.ecommerce.codecase.listing.re2j;

import com.google.re2j.Pattern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Re2jHelper {

    private Re2jHelper() {
        // Private constructor to prevent instantiation
    }

    public static Pattern buildPatternFromFile(String filePath) throws IOException {
        List<String> badWords = loadBadWords(filePath);
        Pattern pattern = buildPattern(badWords);
        return pattern;
    }

    private static Pattern buildPattern(List<String> badWords) {
        StringBuilder patternBuilder = new StringBuilder();
        for (String word : badWords) {
            patternBuilder.append("\\b").append(Pattern.quote(word)).append("\\b|");
        }
        // Remove the trailing '|'
        if (patternBuilder.length() > 0) {
            patternBuilder.setLength(patternBuilder.length() - 1);
        }
        return Pattern.compile(patternBuilder.toString());
    }


    private static List<String> loadBadWords(String filePath) throws IOException {
        List<String> badWords = new ArrayList<>();
        try (InputStream inputStream = Re2jHelper.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                badWords.add(line.trim());
            }
        }
        return badWords;
    }


}
