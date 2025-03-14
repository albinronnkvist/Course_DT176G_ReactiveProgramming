package com.dt176g.project.models.chat.responders;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.models.chat.contexts.Context;
import com.dt176g.project.models.chat.contexts.PaymentSubContext;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentResponderTest {
    private static Stream<String> refundSubContextWordsTestCases() {
        return PaymentSubContext.REFUND.getWords().stream();
    }
    @ParameterizedTest
    @MethodSource("refundSubContextWordsTestCases")
    public void getResponse_ShouldMatchRefundSubContext(String userMessage) {
        var response = PaymentResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.PAYMENT, "Sure, I can help with refunds. Can you provide your order number?"));
    }

    @Test
    public void getResponse_ShouldMatchOrderNumber() {
        final String orderNumber = "12345678";
        var response = PaymentResponder.getContextualResponse(orderNumber);

        assertThat(response).isEqualTo(new ContextualResponse(Context.DEFAULT, "Thank you for providing your order number. Your order has been refunded."));
    }

    @Test
    public void getResponse_WhenNoContextMatch_ShouldReturnFallbackDefaultResponse() {
        final String userMessage = "unknown message";

        var response = PaymentResponder.getContextualResponse(userMessage);

        assertThat(response).isEqualTo(new ContextualResponse(Context.PAYMENT, "I didn't understand that. Can you provide more details?"));
    }
}
