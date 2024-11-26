package se.miun.dt176g.reactive.observables.factoryMethods;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class FromIterable {

    public void run() {
        var items = List.of("Alpha", "Beta", "Gamma");
        Observable.fromIterable(items)
            .map(String::length)
            .filter(i -> i >= 5)
            .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
