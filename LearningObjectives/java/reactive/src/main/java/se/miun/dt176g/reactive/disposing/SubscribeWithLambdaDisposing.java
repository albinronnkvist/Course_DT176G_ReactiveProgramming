package se.miun.dt176g.reactive.disposing;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SubscribeWithLambdaDisposing {
    public void run() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe and capture the Disposable
        Disposable disposable = source.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed!")
        );

        // Let the Observable emit values for 3 seconds
        Thread.sleep(3000);

        // Dispose of the subscription
        System.out.println("Disposing subscription...");
        disposable.dispose();
        System.out.println("Is disposed: " + disposable.isDisposed());

        // Let the program run for a bit longer to verify no further emissions
        System.out.println("Confirming no more emissions...");
        Thread.sleep(3000);
    }
}
