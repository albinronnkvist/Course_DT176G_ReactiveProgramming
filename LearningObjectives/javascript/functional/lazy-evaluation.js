/**
 * Lazy evaluation delays the computation of a value until it is actually needed.
 * Instead of computing values immediately when they are defined, lazy evaluation creates a promise to compute them only when required.
 */

function* lazyFilter(numbers, predicate) {
    for (const num of numbers) {
        if (predicate(num)) yield num;
    }
}

function* lazyMap(numbers, transform) {
    for (const num of numbers) {
        yield transform(num);
    }
}

function lazyProcess(numbers) {
    console.log("Setting up lazy evaluation pipeline...");

    const evens = lazyFilter(numbers, (num) => num % 2 === 0);
    const doubled = lazyMap(evens, (num) => num * 2);

    console.log("Reducing lazily...");
    let sum = 0;
    for (const num of doubled) {
        sum += num;
    }

    return sum;
}

const numbersLazy = Array.from({ length: 1000000 }, (_, i) => i);
console.log(lazyProcess(numbersLazy));


// Eager evaluation in contrast, where values are computed as soon as they are defined.
/* 
function eagerProcess(numbers) {
    console.log("Eagerly filtering even numbers...");
    const evens = numbers.filter((num) => num % 2 === 0);

    console.log("Eagerly mapping numbers...");
    const doubled = evens.map((num) => num * 2);

    console.log("Eagerly reducing numbers...");
    const sum = doubled.reduce((acc, num) => acc + num, 0);

    return sum;
}
*/