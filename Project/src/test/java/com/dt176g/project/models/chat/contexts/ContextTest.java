package com.dt176g.project.models.chat.contexts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

public class ContextTest {
    @ParameterizedTest
    @MethodSource("getWordsTestCases")
    public void getWords_ShouldReturnExpectedList(Context context, List<String> expectedWords) {
        assertThat(context.getWords()).containsExactlyElementsOf(expectedWords);
    }

    @ParameterizedTest
    @MethodSource("getWordsTestCases")
    public void getWords_ShouldReturnImmutableList(Context context) {
        var words = context.getWords();

        assertThatThrownBy(() -> words.add("newWord"));
        assertThatThrownBy(() -> words.remove(0));
        assertThatThrownBy(words::clear);
    }

    private static Stream<Arguments> getWordsTestCases() {
        return Stream.of(
            Arguments.of(Context.DEFAULT, List.of("help", "hi", "hello", "hey")),
            Arguments.of(Context.SHIPPING, List.of("shipping", "delivery")),
            Arguments.of(Context.PAYMENT, List.of("payment"))
        );
    }
}
