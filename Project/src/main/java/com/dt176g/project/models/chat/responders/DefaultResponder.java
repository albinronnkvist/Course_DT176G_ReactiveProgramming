package com.dt176g.project.models.chat.responders;

import java.util.Map;
import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.models.chat.contexts.Context;
import com.dt176g.project.utils.RegexPatternBuilder;

/**
 * Responds to user messages based on matching words in a context.
 * 
 * @author Albin Rönnkvist
 */
public final class DefaultResponder {
    private DefaultResponder() {}

    /**
     * Takes a user message and determines the most appropriate response, 
     * based on matching the user message to words in a context. 
     * If no matching category is found, the method returns a fallback response message.
     * 
     * @param userMessage the user's message
     * @return the bot's contextual response
     */
    public static ContextualResponse getContextualResponse(String userMessage) {
        return lexicon.entrySet().stream()
            .filter(entry -> userMessage.matches(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(getFallback());
    }

    private static final Map<String, ContextualResponse> lexicon = Map.ofEntries(
        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(Context.DEFAULT.getWords()),
            new ContextualResponse(Context.DEFAULT, "Hello! How can I help you?")),
                
        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(Context.SHIPPING.getWords()),
            new ContextualResponse(Context.SHIPPING, "Sure, I can help with shipping. Can you provide more details?")),

        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(Context.PAYMENT.getWords()),
            new ContextualResponse(Context.PAYMENT, "Sure, I can help with payments. Can you provide more details?"))
    );

    private static ContextualResponse getFallback() {
            return new ContextualResponse(Context.DEFAULT, "I didn't understand that. Can you provide more details?");
    }
}
