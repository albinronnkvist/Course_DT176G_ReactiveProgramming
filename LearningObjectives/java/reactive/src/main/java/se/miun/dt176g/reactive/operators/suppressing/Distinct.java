package se.miun.dt176g.reactive.operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

public class Distinct {
    public void run() {
        Observable.just(1, 2, 2, 3, 3)
                .distinct()
                .subscribe(System.out::println); // Emits 1, 2, 3
    }

    public void runWithKeySelector() {
        Observable.just("Alpha", "Beta", "Gamma")
                .distinct(String::length)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
