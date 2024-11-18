package se.miun.dt176g.functional;

/*
 * A deterministic function is a function that, given the same input, always returns the same output.
 */
public class DeterministicFunction {
    public static void exec() {
        System.out.println(deterministicFunction(2, 3)); // Output: 5
        System.out.println(deterministicFunction(2, 3)); // Output: 5
    }

    public static int deterministicFunction(int a, int b) {
        return a + b;
    }
}
