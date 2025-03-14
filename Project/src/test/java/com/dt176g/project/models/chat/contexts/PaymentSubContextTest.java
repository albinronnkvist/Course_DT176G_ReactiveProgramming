package com.dt176g.project.models.chat.contexts;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentSubContextTest {
    @ParameterizedTest
    @MethodSource("getWordsTestCases")
    public void getWords_ShouldReturnExpectedList(PaymentSubContext context, List<String> expectedWords) {
        assertThat(context.getWords()).containsExactlyElementsOf(expectedWords);
    }

    @ParameterizedTest
    @MethodSource("getWordsTestCases")
    public void getWords_ShouldReturnImmutableList(PaymentSubContext context) {
        var words = context.getWords();

        assertThatThrownBy(() -> words.add("newWord"));
        assertThatThrownBy(() -> words.remove(0));
        assertThatThrownBy(words::clear);
    }

    private static Stream<Arguments> getWordsTestCases() {
        return Stream.of(
            Arguments.of(PaymentSubContext.REFUND, List.of("refund"))
        );
    }
}
