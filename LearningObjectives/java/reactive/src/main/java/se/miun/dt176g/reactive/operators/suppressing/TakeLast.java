package se.miun.dt176g.reactive.operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

public class TakeLast {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .takeLast(2)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
