package com.dt176g.project.models.chat.suggesters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dt176g.project.models.chat.contexts.ShippingSubContext;

/**
 * Provides shipping suggestions for user messages.
 * 
 * @author Albin Rönnkvist
 */
public final class ShippingSuggester {
    private ShippingSuggester() {}

    /**
     * Fetches shipping suggestions for user messages.
     * 
     * @return A list of strings representing shipping suggestions.
     */
    public static List<String> getMessageSuggestions() {
        return Stream.of(ShippingSubContext.values())
            .map(subContext -> subContext.getWords().getFirst())
            .collect(Collectors.toList());
    }
}
