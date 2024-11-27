package se.miun.dt176g.reactive.operators.reducing.bool;

import io.reactivex.rxjava3.core.Observable;

public class Contains {
    public void runSatisfied() {
        Observable.range(1, 10000)
                .contains(9563)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runNotSatisfied() {
        Observable.range(1, 100)
                .contains(150)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
