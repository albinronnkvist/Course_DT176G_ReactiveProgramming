package se.miun.dt176g.reactive.operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

public class DistinctUntilChanged {
    public void run() {
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public void runWithKeySelector() {
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                .distinctUntilChanged(String::length)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
