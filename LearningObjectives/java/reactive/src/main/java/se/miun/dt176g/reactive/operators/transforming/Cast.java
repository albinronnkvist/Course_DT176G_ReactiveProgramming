package se.miun.dt176g.reactive.operators.transforming;

import io.reactivex.rxjava3.core.Observable;

public class Cast {
    public void run() {
        Observable<Object> items = Observable.just("Alpha", "Beta", "Gamma")
                .cast(Object.class);

        items.subscribe(System.out::println);
    }
}
