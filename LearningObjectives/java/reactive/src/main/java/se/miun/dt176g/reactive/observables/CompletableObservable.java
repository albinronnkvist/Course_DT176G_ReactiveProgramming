package se.miun.dt176g.reactive.observables;

import io.reactivex.rxjava3.core.Completable;

public class CompletableObservable {
    public void run() {
        var source = Completable.fromRunnable(() -> {
            System.out.println("Action completed successfully");
        });

        source.subscribe(
            () -> System.out.println("Completed"),
            error -> System.out.println("Error: " + error)
        );
    }
}
