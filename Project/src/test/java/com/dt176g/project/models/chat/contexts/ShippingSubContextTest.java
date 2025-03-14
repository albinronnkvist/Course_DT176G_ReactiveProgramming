package com.dt176g.project.models.chat.contexts;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ShippingSubContextTest {
    @ParameterizedTest
    @MethodSource("getWordsTestCases")
    public void getWords_ShouldReturnExpectedList(ShippingSubContext context, List<String> expectedWords) {
        assertThat(context.getWords()).containsExactlyElementsOf(expectedWords);
    }

    @ParameterizedTest
    @MethodSource("getWordsTestCases")
    public void getWords_ShouldReturnImmutableList(ShippingSubContext context) {
        var words = context.getWords();

        assertThatThrownBy(() -> words.add("newWord"));
        assertThatThrownBy(() -> words.remove(0));
        assertThatThrownBy(words::clear);
    }

    private static Stream<Arguments> getWordsTestCases() {
        return Stream.of(
            Arguments.of(ShippingSubContext.TRACKING, List.of("tracking", "track")),
            Arguments.of(ShippingSubContext.DELAY, List.of("delay", "delayed", "late")),
            Arguments.of(ShippingSubContext.COST, List.of("cost", "price"))
        );
    }
}
