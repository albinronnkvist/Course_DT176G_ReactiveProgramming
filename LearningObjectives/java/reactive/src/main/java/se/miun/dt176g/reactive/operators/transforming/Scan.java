package se.miun.dt176g.reactive.operators.transforming;

import io.reactivex.rxjava3.core.Observable;

public class Scan {
    public void run() {
        Observable<String> words = Observable.just("Albin", "Is", "Goated");

        words.scan((accumulated, current) -> accumulated + " " + current)
                .subscribe(
                    result -> System.out.println("Progressive Result: " + result),
                    throwable -> System.err.println("Error: " + throwable),
                    () -> System.out.println("Completed!")
                );
    }
}
