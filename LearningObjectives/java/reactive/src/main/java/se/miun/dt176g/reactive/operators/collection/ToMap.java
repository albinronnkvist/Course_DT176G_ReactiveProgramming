package se.miun.dt176g.reactive.operators.collection;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.rxjava3.core.Observable;

public class ToMap {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithCustomValues() {
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithCustomMap() {
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithDuplicateKeys() {
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(String::length)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
