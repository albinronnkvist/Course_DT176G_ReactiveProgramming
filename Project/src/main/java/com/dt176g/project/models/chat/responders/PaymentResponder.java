package com.dt176g.project.models.chat.responders;

import java.util.Map;
import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.models.chat.contexts.Context;
import com.dt176g.project.models.chat.contexts.PaymentSubContext;
import com.dt176g.project.utils.RegexPatternBuilder;

/**
 * Responds to user messages based on matching words within the payment context.
 * 
 * @author Albin Rönnkvist
 */
public final class PaymentResponder {
    private PaymentResponder() {}

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
        Map.entry(RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(PaymentSubContext.REFUND.getWords()),
                mapToPaymentConversationContext("Sure, I can help with refunds. Can you provide your order number?")),
        
        Map.entry(RegexPatternBuilder.buildOrderNumberPattern(),
                new ContextualResponse(Context.DEFAULT, "Thank you for providing your order number. Your order has been refunded."))
    );

    private static ContextualResponse getFallback() {
        return mapToPaymentConversationContext("I didn't understand that. Can you provide more details?");
    }

    private static ContextualResponse mapToPaymentConversationContext(String response) {
        return new ContextualResponse(Context.PAYMENT, response);
    }
}
