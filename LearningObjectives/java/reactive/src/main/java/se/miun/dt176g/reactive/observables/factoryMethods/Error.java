package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class Error {
    public void run() {
        Observable.error(new Exception("Crash and burn!"))
            .subscribe(
                    i -> System.out.println("RECEIVED: " + i), // onNext (not called)
                    e -> System.out.println("Error captured: " + e), // onError
                    () -> System.out.println("Done!") // onComplete (not called)
            );
    }
}
