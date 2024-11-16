/*
 * immutability is the concept that data, once created, cannot be modified. 
 * Instead, any operation that needs to "change" data will produce a new version of the data, leaving the original data intact.
 */



// Person is an object with properties name and age.
const person = {
    name: "Alice",
    age: 25
};

// Takes the original person object and a new age, 
// then creates a new object using the spread operator (...) to copy the properties of person and update the age property.
function updateAgeImmutable(person, newAge) {
    return { 
        ...person, 
        age: newAge 
    };
}

// updatedPerson is a new object that reflects the updated age, 
// but the original person object remains unchanged.
const updatedPerson = updateAgeImmutable(person, 26);

console.log(person);        // Output: { name: 'Alice', age: 25 }
console.log(updatedPerson);  // Output: { name: 'Alice', age: 26 }
