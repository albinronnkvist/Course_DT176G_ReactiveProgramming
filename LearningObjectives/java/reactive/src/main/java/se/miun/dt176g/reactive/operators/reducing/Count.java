package se.miun.dt176g.reactive.operators.reducing;

import io.reactivex.rxjava3.core.Observable;

public class Count {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .count()
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
