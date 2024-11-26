package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class Range {
    public void run() {
        Observable.range(1, 3)
            .subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    public void runWithOffset() {
        Observable.range(5, 3)
            .subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    public void runWithLong() {
        Observable.rangeLong(1_000_000_000L, 3)
            .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
