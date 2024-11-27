package se.miun.dt176g.reactive.operators.action;

import io.reactivex.rxjava3.core.Observable;

public class DoOnComplete {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnComplete(() -> System.out.println("Source is done emitting!"))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
