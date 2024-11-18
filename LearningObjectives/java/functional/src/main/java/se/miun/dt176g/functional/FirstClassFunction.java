package se.miun.dt176g.functional;

import java.util.function.Function;

/*
 * First-class functions can:
 *  1 Be assigned to variables.
 *  2 Be passed as arguments to other functions.
 *  3 Be returned as values from other functions.
 */
public class FirstClassFunction {
    public static void exec() {

        // 1. Assigning Functions to Variables
        Function<String, String> functionVariable = name -> "Hello, " + name + "!";
        System.out.println(functionVariable.apply("Alice"));
    
        // 2. Passing Functions as Arguments to Other Functions
        String result = functionWithFunctionArgument(name -> "Hello, " + name + "!");
        System.out.println(result);
    
        // 3. Returning Functions from Other Functions
        Function<String, Function<String, String>> functionReturnsFunction = greeting -> 
            name -> greeting + ", " + name + "!";
        
        Function<String, String> sayHello = functionReturnsFunction.apply("Hello");
        System.out.println(sayHello.apply("Alice"));
    }
    
    // Function to demonstrate passing a function as an argument (2.)
    private static String functionWithFunctionArgument(Function<String, String> functionArgument) {
        String name = "Alice";
        return functionArgument.apply(name);
    }
}
