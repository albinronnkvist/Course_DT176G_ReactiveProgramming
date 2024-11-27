package se.miun.dt176g.reactive.operators.reducing.bool;

import io.reactivex.rxjava3.core.Observable;

public class All {
    public void runNotSatisfied() {
        Observable.just(5, 3, 7, 11, 2, 14)
                .all(i -> i < 10)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runSatisfied() {
        Observable.just(2, 4, 6, 8)
                .all(i -> i % 2 == 0)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runEmpty() {
        Observable.<Integer>empty()
                .all(i -> i < 10)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
