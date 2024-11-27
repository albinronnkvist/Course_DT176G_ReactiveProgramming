package se.miun.dt176g.reactive.operators.action;

import io.reactivex.rxjava3.core.Observable;

public class DoAfterNext {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .doAfterNext(s -> System.out.println("After: " + s))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
