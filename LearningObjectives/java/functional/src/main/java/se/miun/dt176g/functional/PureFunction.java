package se.miun.dt176g.functional;

/*
 * A pure function is a function that, given the same input, always returns the same output (deterministic / referential transparency)
 * and has no side effects (it does not modify any external state or rely on mutable data).
 */
public class PureFunction {
    public static void exec() {

        // Pure function
        System.out.println(multiply(3, 4)); // Output: 12
        System.out.println(multiply(3, 4)); // Output: 12
    }
    
    // Pure function: does not depend on or modify external state
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    // In contrast, impure functions have side effects and may return different outputs for the same input
    /*     
    private class ImpureFunction {
        private int multiplier;
        private int iterations;
    
        public ImpureFunction(int multiplier) {
            this.multiplier = multiplier;
            this.iterations = 0;
        }
    
        public int impureMultiply(int a) {
            System.out.println("Logging side effect"); // A side effect (producing console output)
            iterations++;                              // A side effect (modifying instance state)
            return a * multiplier;
        }
        
        public void setMultiplier(int multiplier) {
            this.multiplier = multiplier;             // Modifying external state
        }
    } 
    */
}
    