/*
 * Partial application fixes one or more arguments of a function without fully applying all of them.
 */

// Without Partial Application
function multiply(a, b) {
  return a * b;
}
console.log(multiply(2, 3)); // Output: 6

// With Partial Application
function double(x) {
  return multiply(2, x);
}
console.log(double(5)); // Output: 10



// Example
function partial(fn, ...presetArgs) {
  return function (...laterArgs) {
    return fn(...presetArgs, ...laterArgs);
  };
}

function greaterThan(threshold, value) {
  return value > threshold;
}

const greaterThan10 = partial(greaterThan, 10); // Partial application for threshold = 10

const numbers = [5, 12, 18, 1, 7];
const result = numbers.filter(greaterThan10);

console.log(result); // Output: [12, 18]
