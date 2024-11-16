/*
 * Function composition involves combining two or more functions to create a new function. 
 * The output of one function becomes the input of the next, allowing for the construction of complex operations from simpler ones.
 */

// Simple functions
const double = (x) => x * 2;
const increment = (x) => x + 1;

// Composition function to combine two functions
const compose = (f, g) => (x) => f(g(x));

// Compose `double` and `increment` into a new function
const doubleThenIncrement = compose(increment, double);

console.log(doubleThenIncrement(5)); // Output: double(5) => 10, then increment(10) => 11
