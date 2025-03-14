package com.dt176g.project.models.chat;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.dt176g.project.models.Message;
import com.dt176g.project.models.chat.contexts.Context;
import com.dt176g.project.models.chat.responders.DefaultResponder;
import com.dt176g.project.models.chat.responders.PaymentResponder;
import com.dt176g.project.models.chat.responders.ShippingResponder;
import com.dt176g.project.models.chat.suggesters.DefaultSuggester;
import com.dt176g.project.models.chat.suggesters.PaymentSuggester;
import com.dt176g.project.models.chat.suggesters.ShippingSuggester;

/**
 * Generates bot responses from user messages.
 * It understands the context of the conversation and delegates to the appropriate responder.
 * 
 * @author Albin Rönnkvist
 */
public class ChatModel {
    /**
     * Generates a bot response from a user message, 
     * based on the context of the conversation.
     * 
     * @param previousContextualResponse the previous contextual response
     * @param userMessage the user message to generate a bot response from
     * @return the bot's contextual response
     */
    public static ContextualResponse getContextualBotResponse(ContextualResponse previousContextualResponse, Message userMessage) {        
        return responders.getOrDefault(
                previousContextualResponse.context(), 
                message -> DefaultResponder.getContextualResponse(message)
            )
            .apply(userMessage.text());
    }

    /**
     * Provides a list of message suggestions based on the latest contextual response.
     * 
     * @param latestContextualResponse The latest contextual response, which includes the current context.
     * @return A list of strings representing message suggestions tailored to the current context.
     */
    public static List<String> getMessageSuggestions(ContextualResponse latestContextualResponse) {
        return suggesters.getOrDefault(
                latestContextualResponse.context(), 
                DefaultSuggester.getMessageSuggestions()
            );
    }

    /**
     * Provides the initial bot response when the conversation begins.
     * 
     * @return The initial contextual response.
     */
    public static ContextualResponse getInitialContextualResponse() {
        return new ContextualResponse(
            Context.DEFAULT,
            "Hello! How can I help you?"
        );
    }

    /**
     * Provides the initial bot response with suggestions when the conversation begins.
     * 
     * @return The initial contextual response with suggestions.
     */
    public static ContextualResponseWithSuggestions getInitialContextualBotResponseWithSuggestions() {
        return createResponseWithSuggestions(getInitialContextualResponse());
    }
    
    private static ContextualResponseWithSuggestions createResponseWithSuggestions(ContextualResponse response) {
        return new ContextualResponseWithSuggestions(
            response,
            getMessageSuggestions(response)
        );
    }

    private static final Map<Context, Function<String, ContextualResponse>> responders = Map.of(
        Context.DEFAULT, message -> DefaultResponder.getContextualResponse(message),
        Context.SHIPPING, message -> ShippingResponder.getContextualResponse(message),
        Context.PAYMENT, message -> PaymentResponder.getContextualResponse(message));

    private static final Map<Context, List<String>> suggesters = Map.of(
        Context.DEFAULT, DefaultSuggester.getMessageSuggestions(),
        Context.SHIPPING, ShippingSuggester.getMessageSuggestions(),
        Context.PAYMENT, PaymentSuggester.getMessageSuggestions());
}
