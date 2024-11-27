package se.miun.dt176g.reactive.operators.action;

import io.reactivex.rxjava3.core.Observable;

public class DoOnEach {
    public void run() {
        Observable.just("One", "Two", "Three")
                .doOnEach(notification -> System.out.println("doOnEach: " + notification))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public void runWithDetails() {
        Observable.just("One", "Two", "Three")
                .doOnEach(notification -> System.out.println("doOnEach: Value=" + notification.getValue() +
                        ", Error=" + notification.getError()))
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
