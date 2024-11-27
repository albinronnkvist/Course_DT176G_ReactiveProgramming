package se.miun.dt176g.reactive.operators.collection;

import io.reactivex.rxjava3.core.Observable;

public class ToMultiMap {
    public void runWithDuplicateKeys() {
        Observable.just("Alpha", "Beta", "Gamma")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
