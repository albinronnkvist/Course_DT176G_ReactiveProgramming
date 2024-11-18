package se.miun.dt176g.functional;

import java.util.function.Function;

/*
 * Function composition involves combining two or more functions to create a new function. 
 * The output of one function becomes the input of the next, allowing for the construction of complex operations from simpler ones.
 */
public class FunctionComposition {
    public static void exec() {

        // Simple functions
        Function<Integer, Integer> doubleFunction = x -> x * 2;
        Function<Integer, Integer> incrementFunction = x -> x + 1;
    
        // Compose simple functions into a new function
        Function<Integer, Integer> doubleThenIncrement = compose(incrementFunction, doubleFunction);
    
        System.out.println(doubleThenIncrement.apply(5)); // Output: double(5) => 10, then increment(10) => 11
    }
    

    /**
     * Compose two functions `f` and `g` into a new function: f(g(x)).
     *
     * @param f The outer function.
     * @param g The inner function.
     * @return A composed function f(g(x)).
     */
    public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
        return g.andThen(f); // Equivalent to: x -> f.apply(g.apply(x))
    }
}
