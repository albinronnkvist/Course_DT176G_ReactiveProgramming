/*
 * A higher-order function can:
 *  - Take one or more functions as arguments
 *  - Return a function as its result
*/



// 1. Taking Functions as Arguments
function applyOperation(a, b, operation) {
    return operation(a, b);
}

const add = (x, y) => x + y;
const multiply = (x, y) => x * y;

console.log(applyOperation(5, 3, add));
console.log(applyOperation(5, 3, multiply));



// 2. Returning Functions as Result
function greet(greeting) {
    return function(name) {
        return `${greeting}, ${name}!`;
    };
}

const sayHello = greet("Hello");
const sayGoodbye = greet("Goodbye");

console.log(sayHello("Alice"));    // Output: Hello, Alice!
console.log(sayGoodbye("Bob"));    // Output: Goodbye, Bob!
