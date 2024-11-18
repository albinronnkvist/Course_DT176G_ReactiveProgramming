package se.miun.dt176g.reactive.operators;

import io.reactivex.rxjava3.core.Observable;

public class MapOperator {
    public void run() {
        Observable<Integer> numbers = Observable.just(1, 2, 3, 4, 5);

        numbers.map(number -> number * number)
               .subscribe(
                   square -> System.out.println("Square: " + square), // onNext
                   throwable -> System.err.println("Error: " + throwable), // onError
                   () -> System.out.println("Completed!") // onComplete
               );
    }
}
