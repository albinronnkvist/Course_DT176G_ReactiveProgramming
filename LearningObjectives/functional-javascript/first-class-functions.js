/*
 * First-class functions can:
 *  1 Be assigned to variables.
 *  2 Be passed as arguments to other functions.
 *  3 Be returned as values from other functions.
 */



// 1. Assigning Functions to Variables
const functionVariable = function(name) {
    return `Hello, ${name}!`;
};

console.log(functionVariable("Alice"));



// 2. Passing Functions as Arguments to Other Functions
function functionWithFunctionArgument(functionArgument) {
    const name = "Alice";
    return functionArgument(name);
}

const functionPassedAsArgument = function(name) {
    return `Hello, ${name}!`;
};

console.log(functionWithFunctionArgument(functionPassedAsArgument));



// 3. Returning Functions from Other Functions
function functionReturnsFunction(greeting) {
    return function(name) {
        return `${greeting}, ${name}!`;
    };
}

const sayHello = functionReturnsFunction("Hello");
console.log(sayHello("Alice"));
