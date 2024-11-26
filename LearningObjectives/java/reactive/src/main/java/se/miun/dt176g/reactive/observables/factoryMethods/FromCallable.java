package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class FromCallable {
    public void runWithoutFromCallable() {
        Observable.just(1 / 0) // Throws ArithmeticException immediately
            .subscribe(
                i -> System.out.println("RECEIVED: " + i),
                e -> System.out.println("Error captured: " + e)
            );
    }

    public void runWithFromCallable() {
        Observable.fromCallable(() -> 1 / 0) // Lazy computation
            .subscribe(
                i -> System.out.println("Received: " + i),
                e -> System.out.println("Error captured: " + e) // Error is caught here instead
            );
    }
}
