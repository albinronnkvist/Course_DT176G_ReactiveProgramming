package com.dt176g.project.models.chat.contexts;

import java.util.List;

/**
 * Enum representing sub-categories for the chat context payment category, 
 * each associated with an unmodifiable list of predefined words for describing the sub-category.
 * 
 * @author Albin Rönnkvist
 */
public enum PaymentSubContext {
    REFUND(List.of("refund"));

    private final List<String> words;

    private PaymentSubContext(List<String> words) {
        this.words = words;
    }

    /**
     * Retrieves an unmodifiable list of words associated with this sub-category.
     *
     * @return The list of words for this sub-category.
     */
    public List<String> getWords() {
        return words;
    }
}
