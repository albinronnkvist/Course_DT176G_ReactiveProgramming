package se.miun.dt176g.reactive.operators.collection;

import io.reactivex.rxjava3.core.Observable;

public class ToSortedList {
    public void run() {
        Observable.just("Beta", "Gamma", "Alpha")
                .toSortedList()
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithInitialCapacity() {
        Observable.range(1, 1000)
                .toSortedList(1000) // Initial capacity hint
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithCustomList() {
        Observable.just("Beta", "Gamma", "Alpha")
                .toSortedList((a, b) -> b.compareTo(a)) // Reverse lexicographical order
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
