package com.dt176g.project.models.chat.responders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.models.chat.contexts.Context;
import com.dt176g.project.models.chat.contexts.ShippingSubContext;

public class ShippingResponderTest {
    private static Stream<String> trackingSubContextWordsTestCases() {
        return ShippingSubContext.TRACKING.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("trackingSubContextWordsTestCases")
    public void getResponse_ShouldMatchTrackingSubContext(String userMessage) {
        var response = ShippingResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.SHIPPING, "Sure, I can help with tracking your order. Can you provide your order number?"));
    }

    private static Stream<String> delaySubContextWordsTestCases() {
        return ShippingSubContext.DELAY.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("delaySubContextWordsTestCases")
    public void getResponse_ShouldMatchDelaySubContext(String userMessage) {
        var response = ShippingResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.SHIPPING, "I’m sorry for the delay. Can you provide your order number?"));
    }

    private static Stream<String> costSubContextWordsTestCases() {
        return ShippingSubContext.COST.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("costSubContextWordsTestCases")
    public void getResponse_ShouldMatchCostSubContext(String userMessage) {
        var response = ShippingResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.SHIPPING, "Shipping costs vary depending on your location and items. View acme.com/shipping for further details."));
    }

    @Test
    public void getResponse_ShouldMatchOrderNumber() {
        final String orderNumber = "12345678";
        var response = ShippingResponder.getContextualResponse(orderNumber);

        assertThat(response).isEqualTo(new ContextualResponse(Context.DEFAULT, "Thank you for providing your order number. Your order is currently at our warehouse in Stockholm and is estimated to arrive in 1-2 business days."));
    }

    @Test
    public void getResponse_WhenNoContextMatch_ShouldReturnFallbackDefaultResponse() {
        final String userMessage = "unknown message";

        var response = ShippingResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.SHIPPING, "I didn't understand that. Can you provide more details?"));
    }
}
