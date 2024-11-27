package se.miun.dt176g.reactive.operators.action;

import io.reactivex.rxjava3.core.Observable;

public class DoOnSubscribe {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
