package com.dt176g.project.models.mappers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.dt176g.project.models.Message;
import com.dt176g.project.models.SenderType;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageMapperTest {
    @ParameterizedTest
    @ValueSource(strings = {
        "Your order has been shipped.",
        "Payment confirmed successfully.",
        ""
    })
    public void mapToBotMessage_FromString_ShouldReturnMessageWithSameTextAndBotSenderType(String text)  {
        var message = MessageMapper.mapToBotMessage(text);

        assertThat(message).isEqualTo(new Message(text, SenderType.BOT));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Hi",
        "Shipping",
        ""
    })
    public void mapToUserMessage_ShouldReturnMessageWithSameTextAndBotSenderType(String text)  {
        var message = MessageMapper.mapToUserMessage(text);

        assertThat(message).isEqualTo(new Message(text, SenderType.USER));
    }
}
