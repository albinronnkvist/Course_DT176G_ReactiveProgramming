package se.miun.dt176g.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Currying is a technique where a function is transformed into a sequence of functions, each taking a single argument. 
 */
public class Currying {
    public static void exec() {

        // Without Currying
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(2, 3)); // Output: 5

        // With Currying: Transform add into a curried function
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;

        Function<Integer, Integer> add2 = curriedAdd.apply(2); // Partially applied function
        System.out.println(add2.apply(3)); // Output: 5
        System.out.println(curriedAdd.apply(2).apply(3)); // Output: 5


        // ----------------------
        // Example: Filtering numbers greater than a threshold
        Function<Integer, Function<Integer, Boolean>> isGreaterThan = a -> b -> b > a;

        // Partially applied function
        Function<Integer, Boolean> greaterThan10 = isGreaterThan.apply(10);

        // Filtering a list with the curried function
        List<Integer> numbers = Arrays.asList(5, 12, 18, 1, 7);
        List<Integer> result = numbers.stream()
                .filter(x -> greaterThan10.apply(x)) // Apply the curried function
                .collect(Collectors.toList());

        System.out.println(result); // Output: [12, 18]
    }
}
