package com.dt176g.project.models.chat.responders;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.models.chat.contexts.Context;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultResponderTest {

    private static Stream<String> defaultContextWordsTestCases() {
        return Context.DEFAULT.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("defaultContextWordsTestCases")
    public void getResponse_ShouldReturnDefaultContextResponse(String userMessage) {
        var response = DefaultResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.DEFAULT, "Hello! How can I help you?"));
    }

    private static Stream<String> shippingContextWordsTestCases() {
        return Context.SHIPPING.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("shippingContextWordsTestCases")
    public void getResponse_ShouldReturnShippingContextResponse(String userMessage) {
        var response = DefaultResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.SHIPPING, "Sure, I can help with shipping. Can you provide more details?"));
    }

    private static Stream<String> paymentContextWordsTestCases() {
        return Context.PAYMENT.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("paymentContextWordsTestCases")
    public void getResponse_ShouldReturnPaymentContextResponse(String userMessage) {
        var response = DefaultResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.PAYMENT, "Sure, I can help with payments. Can you provide more details?"));
    }

    @Test
    public void getResponse_WhenNoContextMatch_ShouldReturnFallbackDefaultResponse() {
        final String userMessage = "unknown message";

        var response = DefaultResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.DEFAULT, "I didn't understand that. Can you provide more details?"));
    }
}
