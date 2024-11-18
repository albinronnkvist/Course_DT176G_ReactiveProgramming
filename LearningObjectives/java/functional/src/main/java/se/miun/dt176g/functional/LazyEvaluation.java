package se.miun.dt176g.functional;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * Lazy evaluation delays the computation of a value until it is actually needed.
 * Instead of computing values immediately when they are defined, lazy evaluation creates a promise to compute them only when required.
 */
public class LazyEvaluation {
    public static void exec() {
        lazyEvaluation();
    }

    private static void lazyEvaluation() {
        IntStream numbersLazy = IntStream.range(0, 1_000_000);

        System.out.println("Setting up lazy evaluation pipeline...");

        // Lazy evaluation pipeline
        int result = numbersLazy
                .filter(num -> num % 2 == 0)
                .peek(num -> System.out.println("Filtered: " + num))
                .map(num -> num * 2)
                .peek(num -> System.out.println("Mapped: " + num))
                .reduce(0, (sum, num) -> {
                    System.out.println("Reducing: " + num);
                    return sum + num;
                });

        System.out.println("Result: " + result);
    }

    public static void eagerEvaluation() {
        IntStream numbers = IntStream.range(0, 1_000_000);

        System.out.println("Eagerly filtering numbers...");
        int[] evens = numbers.filter(num -> num % 2 == 0).toArray();

        System.out.println("Eagerly mapping numbers...");
        int[] doubled = Arrays.stream(evens).map(num -> num * 2).toArray();

        System.out.println("Eagerly reducing numbers...");
        int result = Arrays.stream(doubled).sum();

        System.out.println("Result: " + result);
    }
}
