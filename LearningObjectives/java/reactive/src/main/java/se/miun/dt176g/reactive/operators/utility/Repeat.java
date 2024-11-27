package se.miun.dt176g.reactive.operators.utility;

import io.reactivex.rxjava3.core.Observable;

public class Repeat {
    public void runInfinite() {
        Observable.just("One", "Two")
                .repeat()
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runFinite() {
        Observable.just("One", "Two")
                .repeat(2)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
