package se.miun.dt176g.reactive.observables;

import io.reactivex.rxjava3.core.Maybe;

public class MaybeObservable {
    public void runWithValue() {
        Maybe.just("Hello, Maybe!")
            .subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.out.println("Error: " + error),
                () -> System.out.println("Completed without item")
            );
    }

    public void runWithoutValue() {
        Maybe.empty()
            .subscribe(
                item -> System.out.println("Received: " + item),
                error -> System.out.println("Error: " + error),
                () -> System.out.println("Completed without item")
            );
    }
}
