/*
 * A closure is a function that remembers and can access variables from its lexical scope, 
 * even after the outer function that created it has finished executing.
 * 
 * Note: Lexical scope refers to the region in the code where a variable is defined and accessible based on the structure of the code
 */

function makeCounter() {
  var count = 0; // Encapsulated state
  return function () {
    count += 1;
    return count;
  };
}

var x = makeCounter();

// Every call to x() operates on the same 'count' variable 
console.log(x()); // Output: 1
console.log(x()); // Output: 2
console.log(x()); // Output: 3
