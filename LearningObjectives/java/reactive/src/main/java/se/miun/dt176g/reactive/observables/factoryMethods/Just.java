package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class Just {
    public void run() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source
            .map(String::length)
            .filter(i -> i >= 5)
            .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
