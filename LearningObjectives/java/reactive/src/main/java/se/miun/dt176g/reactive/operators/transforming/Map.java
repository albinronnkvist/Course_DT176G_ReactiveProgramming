package se.miun.dt176g.reactive.operators.transforming;

import io.reactivex.rxjava3.core.Observable;

public class Map {
    public void run() {
        Observable<Integer> numbers = Observable.just(1, 2, 3, 4, 5);

        numbers.map(number -> number * number)
               .subscribe(
                   square -> System.out.println("Square: " + square),
                   throwable -> System.err.println("Error: " + throwable),
                   () -> System.out.println("Completed!")
               );
    }
}
