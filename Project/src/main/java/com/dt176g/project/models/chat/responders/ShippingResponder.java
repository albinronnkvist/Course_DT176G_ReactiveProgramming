package com.dt176g.project.models.chat.responders;

import java.util.Map;
import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.models.chat.contexts.Context;
import com.dt176g.project.models.chat.contexts.ShippingSubContext;
import com.dt176g.project.utils.RegexPatternBuilder;

/**
 * Responds to user messages based on matching words within the shipping context.
 * 
 * @author Albin Rönnkvist
 */
public final class ShippingResponder {
    private ShippingResponder() {}

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
        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(ShippingSubContext.TRACKING.getWords()),
                mapToShippingConversationContext("Sure, I can help with tracking your order. Can you provide your order number?")),
        
        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(ShippingSubContext.DELAY.getWords()),
                mapToShippingConversationContext("I’m sorry for the delay. Can you provide your order number?")),
        
        
        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(ShippingSubContext.COST.getWords()),
            mapToShippingConversationContext("Shipping costs vary depending on your location and items. View acme.com/shipping for further details.")),

        Map.entry(RegexPatternBuilder.buildOrderNumberPattern(),
                new ContextualResponse(Context.DEFAULT, ("Thank you for providing your order number. Your order is currently at our warehouse in Stockholm and is estimated to arrive in 1-2 business days.")))
    );

    private static ContextualResponse getFallback() {
        return mapToShippingConversationContext("I didn't understand that. Can you provide more details?");
    }

    private static ContextualResponse mapToShippingConversationContext(String response) {
        return new ContextualResponse(Context.SHIPPING, response);
    }
}
