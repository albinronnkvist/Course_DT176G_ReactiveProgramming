package com.dt176g.project.utils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A utility class for creating reusable regular expression patterns.
 * 
 * @author Albin Rönnkvist
 */
public final class RegexPatternBuilder {
    private RegexPatternBuilder() {}
    
    /**
     * Creates a regular expression pattern that matches any word in the given list, in a case insensitive manner.
     *
     * @param words The list of words to match.
     * @return The regular expression pattern.
     */
    public static String buildCaseInsensitiveExactWordPattern(List<String> words) {
        return "(?i).*\\b" + joinWords(words) + "\\b.*";
    }

    /**
     * Creates a regular expression pattern that matches a string if it contains exactly 8 digits.
     *
     * @return The regular expression pattern.
     */
    public static String buildOrderNumberPattern() {
        return "^\\d{8}$";
    }

    private static String joinWords(List<String> words) {
        return words.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
}
