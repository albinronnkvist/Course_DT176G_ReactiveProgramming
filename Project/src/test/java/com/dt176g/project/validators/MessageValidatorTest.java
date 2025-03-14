package com.dt176g.project.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.dt176g.project.models.validators.MessageValidator;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {
        "Text",
        " Text ",
        "    Hello",
        "Hello world! How are you?"
    })
    public void isValid_ShouldReturnTrueForValidMessages(String text)  {
        assertThat(MessageValidator.isValid(text)).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "  "
    })
    public void isValid_ShouldReturnFalseForBlankMessages(String text)  {
        assertThat(MessageValidator.isValid(text)).isEqualTo(false);
    }

    @Test
    public void isValid_ShouldReturnFalseForNullMessages()  {
        assertThat(MessageValidator.isValid(null)).isEqualTo(false);
    }
}
