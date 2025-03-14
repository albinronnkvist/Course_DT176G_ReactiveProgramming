package com.dt176g.project.utils;

/**
 * Utility class for string handling.
 * 
 * @author Albin Rönnkvist
 */
public final class StringHelper {
    private StringHelper() {}
    
    /**
     * Checks if a string is null or blank.
     *
     * @param message the string to check
     * @return true if the string is null or blank, false otherwise
     */
    public static boolean isNullOrBlank(final String message) {
        return message == null || message.isBlank();
    }
}
