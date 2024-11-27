package se.miun.dt176g.reactive.operators.collection;

import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Observable;

public class ToList {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .toList()
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithInitialCapacity() {
        Observable.range(1, 1000)
                .toList(1000)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithCustomList() {
        Observable.just("Beta", "Gamma", "Alpha")
                .toList(CopyOnWriteArrayList::new)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
