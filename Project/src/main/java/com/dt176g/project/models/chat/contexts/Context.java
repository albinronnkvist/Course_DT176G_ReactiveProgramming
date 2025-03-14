package com.dt176g.project.models.chat.contexts;

import java.util.List;

/**
 * Enum representing chat context categories, 
 * each associated with an unmodifiable list of predefined words for describing the category.
 * 
 * @author Albin Rönnkvist
 */
public enum Context {
    DEFAULT(List.of("help", "hi", "hello", "hey")),
    SHIPPING(List.of("shipping", "delivery")),
    PAYMENT(List.of("payment"));

    private final List<String> words;

    private Context(List<String> words) {
        this.words = words;
    }

    /**
     * Retrieves an unmodifiable list of words associated with this category.
     *
     * @return The list of words for this category.
     */
    public List<String> getWords() {
        return words;
    }
}
