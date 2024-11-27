package se.miun.dt176g.reactive.operators.utility;

import io.reactivex.rxjava3.core.Observable;

public class Single {
    public void runSingleEmission() {
        Observable.just("One")
                .single("Four")
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public void runNoEmissions() {
        Observable.empty()
                .single("Four")
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public void runMultipleEmissions() {
        Observable.just("One", "Two")
                .single("Four")
                .subscribe(
                        i -> System.out.println("Received: " + i),
                        e -> System.out.println("Error: " + e));
    }
}
