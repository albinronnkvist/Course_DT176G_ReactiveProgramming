/*
 * A pure function is a function that, given the same input, always returns the same output (deterministic)
 * and has no side effects (it does not modify any external state or rely on mutable data).
 */



// Pure function
function multiply(a, b) {
    return a * b;
}

console.log(multiply(3, 4)); // Output: 12
console.log(multiply(3, 4)); // Output: 12



// Impure function
let multiplier = 2;

function impureMultiply(a) {
    return a * multiplier;
}

console.log(impureMultiply(3)); // Output: 6 (3 * 2)
multiplier = 4;                 // Changing external state
console.log(impureMultiply(3)); // Output: 12 (3 * 4)
