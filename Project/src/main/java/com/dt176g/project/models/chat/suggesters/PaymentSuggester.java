package com.dt176g.project.models.chat.suggesters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dt176g.project.models.chat.contexts.PaymentSubContext;

/**
 * Provides payment suggestions for user messages.
 * 
 * @author Albin Rönnkvist
 */
public final class PaymentSuggester {
    private PaymentSuggester() {}

    /**
     * Fetches payment suggestions for user messages.
     * 
     * @return A list of strings representing payment suggestions.
     */
    public static List<String> getMessageSuggestions() {
        return Stream.of(PaymentSubContext.values())
            .map(subContext -> subContext.getWords().getFirst())
            .collect(Collectors.toList());
    }
}
