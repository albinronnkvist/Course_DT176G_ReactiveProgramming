/*
 * Currying is a technique where a function is transformed into a sequence of functions, each taking a single argument. 
 */

// Without Currying
function add(a, b) {
  return a + b;
}
console.log(add(2, 3)); // Output: 5

// With Currying
function curriedAdd(a) {
  return function (b) {
    return a + b;
  };
}
const add2 = curriedAdd(2); // Partially applied function
console.log(add2(3)); // Output: 5
console.log(curriedAdd(2)(3)); // Output: 5



// Example
const isGreaterThan = a => b => b > a;

const greaterThan10 = isGreaterThan(10); // Partially applied function

const numbers = [5, 12, 18, 1, 7];
const result = numbers.filter(greaterThan10); // Apply the curried function

console.log(result); // Output: [12, 18]