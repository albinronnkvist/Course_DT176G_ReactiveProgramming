package com.dt176g.project.models.chat.suggesters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dt176g.project.models.chat.contexts.Context;

/**
 * Provides default suggestions for user messages.
 * 
 * @author Albin Rönnkvist
 */
public final class DefaultSuggester {
    private DefaultSuggester() {}

    /**
     * Fetches default suggestions for user messages.
     * 
     * @return A list of strings representing default suggestions.
     */
    public static List<String> getMessageSuggestions() {
        return Stream.of(Context.values())
            .map(context -> context.getWords().getFirst())
            .collect(Collectors.toList());
    }
}
