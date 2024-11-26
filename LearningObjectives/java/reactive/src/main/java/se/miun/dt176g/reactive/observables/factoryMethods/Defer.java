package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class Defer {
    private static int start = 1;
    private static int count = 3;

    public void runWithoutDefer() {
        Observable<Integer> source = Observable.range(start, count);

        source.subscribe(i -> System.out.println("Observer 1: " + i));

        // Modify count
        count = 5;

        // Still only counts to 3
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }

    public void runWithDefer() {
        Observable<Integer> source = Observable.defer(() -> 
            Observable.range(start, count));

        source.subscribe(i -> System.out.println("Observer 1: " + i));

        // Modify count
        count = 5;

        // Now counts to 5
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}
