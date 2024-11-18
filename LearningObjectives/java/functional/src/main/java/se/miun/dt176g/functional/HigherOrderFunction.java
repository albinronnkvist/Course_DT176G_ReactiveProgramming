package se.miun.dt176g.functional;

import java.util.function.BiFunction;
import java.util.function.Function;

/*
 * A higher-order function can:
 *  - Take one or more functions as arguments
 *  - Return a function as its result
*/
public class HigherOrderFunction {
    public static void exec() {

        // 1. Taking Functions as Arguments
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
    
        System.out.println(applyOperation(5, 3, add));        // Output: 8
        System.out.println(applyOperation(5, 3, multiply));   // Output: 15
    
        // 2. Returning Functions as Results
        Function<String, Function<String, String>> greet = greeting ->
            name -> greeting + ", " + name + "!";
    
        Function<String, String> sayHello = greet.apply("Hello");
        Function<String, String> sayGoodbye = greet.apply("Goodbye");
    
        System.out.println(sayHello.apply("Alice"));          // Output: Hello, Alice!
        System.out.println(sayGoodbye.apply("Bob"));          // Output: Goodbye, Bob!
    }
    
    // Higher-order function: takes a BiFunction as an argument
    public static int applyOperation(int a, int b, BiFunction<Integer, Integer, Integer> operation) {
        return operation.apply(a, b);
    }
}
