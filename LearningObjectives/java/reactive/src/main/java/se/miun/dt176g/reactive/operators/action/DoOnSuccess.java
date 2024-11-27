package se.miun.dt176g.reactive.operators.action;

import io.reactivex.rxjava3.core.Observable;

public class DoOnSuccess {
    public void run() {
        Observable.just(5, 3, 7)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
