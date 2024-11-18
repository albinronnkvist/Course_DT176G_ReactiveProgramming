package se.miun.dt176g.functional;

/**
 * Recursion involve a function calling itself to solve smaller sub-problems of a larger problem.
 */
public class Recursion {
    public static void exec() {
        int number = 5;
        int result = factorial(number);
        System.out.println("Factorial of " + number + " is: " + result); // Output: 120
    }

    public static int factorial(int n) {
        // Base case: Factorial of 0 is 1
        if (n == 0) {
            return 1;
        }

        // Recursive case: n * factorial(n - 1)
        return n * factorial(n - 1);
    }
}
