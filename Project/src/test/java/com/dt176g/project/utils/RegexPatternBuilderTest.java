package com.dt176g.project.utils;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexPatternBuilderTest {
    private static Stream<Arguments> buildCaseInsensitiveExactWordPatternTestCases() {
        return Stream.of(
            Arguments.of(List.of("hello", "world"), "hello", true),
            Arguments.of(List.of("hello", "world"), "Hello", true),
            Arguments.of(List.of("hello", "world"), "world", true),
            Arguments.of(List.of("hello", "world"), "WORLD", true),
            Arguments.of(List.of("hello", "world"), "", false),
            Arguments.of(List.of("hello", "world"), "  ", false),
            Arguments.of(List.of("hello", "world"), "planet", false),
            Arguments.of(List.of("hello", "world"), "hell o", false)
        );
    }
    @ParameterizedTest
    @MethodSource("buildCaseInsensitiveExactWordPatternTestCases")
    public void buildCaseInsensitiveExactWordPattern_ShouldMatch_ExactWordIgnoringCase(List<String> words, String input, boolean expectedMatch) {
        var pattern = RegexPatternBuilder.buildCaseInsensitiveExactWordPattern(words);
        
        assertThat(input.matches(pattern)).isEqualTo(expectedMatch);
    }

    private static Stream<Arguments> buildOrderNumberPatternTestCases() {
        return Stream.of(
            Arguments.of("12345678", true),
            Arguments.of("22535649", true),
            Arguments.of("1234567", false),
            Arguments.of("123456789", false),
            Arguments.of("12345abc", false),
            Arguments.of("abcdefgh", false),
            Arguments.of("", false),
            Arguments.of("   ", false)
        );
    }
    @ParameterizedTest
    @MethodSource("buildOrderNumberPatternTestCases")
    public void buildOrderNumberPattern_ShouldMatch_EightDigitOrderNumber(String input, boolean expectedMatch) {
        var pattern = RegexPatternBuilder.buildOrderNumberPattern();

        assertThat(input.matches(pattern)).isEqualTo(expectedMatch);
    }
}
