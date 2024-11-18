package se.miun.dt176g.functional;

import java.util.Arrays;
import java.util.Comparator;

/*
 * A closure is a function that remembers and can access variables from its lexical scope, 
 * even after the outer function that created it has finished executing.
 * 
 * Note: Lexical scope refers to the region in the code where a variable is defined and accessible based on the structure of the code
 */
public class Closure {
    public static void exec() {
        final int numberToCompareTo = 10;

        Comparator<Integer> comp = (a, b) -> {
            int result = 0;
            if (a < numberToCompareTo) result -= 1;
            if (b < numberToCompareTo) result += 1;
            return result;
        };

        Integer[] array = new Integer[]{1, 10, 5, 15, 6, 20, 21, 3, 7};

        Arrays.sort(array, comp);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
