package se.miun.dt176g.reactive;

import io.reactivex.rxjava3.core.Observable;

public class ZipOperator {
    public void run() {
        Observable<String> names = Observable.fromArray("Alice", "Bob", "Charlie", "David", "Eve");
        Observable<Integer> ages = Observable.fromArray(25, 30, 35, 40, 45);

        Observable
                .zip(names, ages, (name, age) -> name + " is " + age + " years old.")
                .filter(data -> data.contains("Charlie"))
                .map(data -> data.toUpperCase())
                .subscribe(data -> System.out.println(data));
    }
}
