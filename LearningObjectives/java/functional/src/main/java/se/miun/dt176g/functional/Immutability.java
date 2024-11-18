package se.miun.dt176g.functional;

import se.miun.dt176g.functional.other.Person;

/*
 * Immutability is the concept that data, once created, cannot be modified. 
 * Instead, any operation that needs to "change" data will produce a new version of the data, leaving the original data intact.
 */
public class Immutability {
    public static void exec() {

        // Immutable `Person` object
        Person person = new Person("Alice", 25);

        // Create a new Person object with updated age
        Person updatedPerson = updateAgeImmutable(person, 26);

        // Original object remains unchanged
        System.out.println(person); // Output: Person{name='Alice', age=25}
        System.out.println(updatedPerson); // Output: Person{name='Alice', age=26}
    }

    public static Person updateAgeImmutable(Person person, int newAge) {
        return new Person(person.getName(), newAge);
    }
}
