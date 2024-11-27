package se.miun.dt176g.reactive.operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

public class Filter {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
            .filter(s -> s.startsWith("A"))
            .subscribe(i -> System.out.println("RECEIVED: " + i),
                       e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
