package com.dt176g.project.models.chat.contexts;

import java.util.List;

/**
 * Enum representing sub-categories for the chat context shipping category, 
 * each associated with an unmodifiable list of predefined words for describing the sub-category.
 * 
 * @author Albin Rönnkvist
 */
public enum ShippingSubContext {
    TRACKING(List.of("tracking", "track")),
    DELAY(List.of("delay", "delayed", "late")),
    COST(List.of("cost", "price"));

    private final List<String> words;

    private ShippingSubContext(List<String> words) {
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
