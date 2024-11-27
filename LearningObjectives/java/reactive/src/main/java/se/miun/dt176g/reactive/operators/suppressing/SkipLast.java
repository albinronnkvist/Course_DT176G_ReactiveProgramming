package se.miun.dt176g.reactive.operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

public class SkipLast {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
            .skipLast(2)
            .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
