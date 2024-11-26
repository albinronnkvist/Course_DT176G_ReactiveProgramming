package se.miun.dt176g.reactive.observables;

import io.reactivex.rxjava3.core.Single;

public class SingleObservable {
    public void run() {
        Single.just("Hello!")
            .map(String::length)
            .subscribe(
                System.out::println, // onSuccess
                e -> System.out.println("Error captured: " + e) // onError
            );
    }
}
