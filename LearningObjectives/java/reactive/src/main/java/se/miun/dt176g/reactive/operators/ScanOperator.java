package se.miun.dt176g.reactive.operators;

import io.reactivex.rxjava3.core.Observable;

public class ScanOperator {
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
