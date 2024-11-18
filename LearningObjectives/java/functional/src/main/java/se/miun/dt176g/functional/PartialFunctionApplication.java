package se.miun.dt176g.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Partial application fixes one or more arguments of a function without fully applying all of them.
 */
public class PartialFunctionApplication {
    public static void exec() {

        // Without Partial Application
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        System.out.println(multiply.apply(2, 3)); // Output: 6

        // With Partial Application
        Function<Integer, Integer> doubleFunction = partialInteger(multiply, 2);
        System.out.println(doubleFunction.apply(3)); // Output: 6


        // ---------------------------------------------------
        // Example: Filtering numbers greater than a threshold
        BiFunction<Integer, Integer, Boolean> greaterThan = (threshold, value) -> value > threshold;

        // Partial application for threshold = 10
        Function<Integer, Boolean> greaterThan10 = partialBoolean(greaterThan, 10);

        // Filtering a list with partially applied function
        List<Integer> numbers = Arrays.asList(5, 12, 18, 1, 7);
        List<Integer> result = numbers.stream()
                .filter(x -> greaterThan10.apply(x)) // Use the partially applied function
                .collect(Collectors.toList());

        System.out.println(result); // Output: [12, 18]
    }

    public static Function<Integer, Integer> partialInteger(BiFunction<Integer, Integer, Integer> fn, Integer presetArg) {
        return laterArg -> fn.apply(presetArg, laterArg);
    }

    public static Function<Integer, Boolean> partialBoolean(BiFunction<Integer, Integer, Boolean> fn, Integer presetArg) {
        return laterArg -> fn.apply(presetArg, laterArg);
    }
}
