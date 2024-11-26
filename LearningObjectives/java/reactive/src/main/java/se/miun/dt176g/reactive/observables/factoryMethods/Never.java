package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class Never {
    public void run() throws InterruptedException {
        var never = Observable.never();

        never.subscribe(
                System.out::println,                // onNext (never called)
                Throwable::printStackTrace,         // onError (never called)
                () -> System.out.println("Done!")   // onComplete (never called)
        );

        System.out.println("Waiting for emissions...");
        Thread.sleep(3000);
        System.out.println("Exiting application.");
    }
}
