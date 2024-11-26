package se.miun.dt176g.reactive.disposing;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CompositeDisposing {
    private static final CompositeDisposable disposables = new CompositeDisposable();

    public void run() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe two Observers and capture their Disposables
        Disposable disposable1 = source.subscribe(
            l -> System.out.println("Observer 1: " + l)
        );

        Disposable disposable2 = source.subscribe(
            l -> System.out.println("Observer 2: " + l)
        );

        // Add the Disposables to the CompositeDisposable
        disposables.addAll(disposable1, disposable2);

        // Let the Observables emit for 5 seconds
        Thread.sleep(5000);

        // Dispose all Disposables at once
        System.out.println("Disposing all subscriptions...");
        disposables.dispose();
        System.out.println("Is Observer 1 disposed: " + disposable1.isDisposed());
        System.out.println("Is Observer 2 disposed: " + disposable2.isDisposed());
        System.out.println("Is all disposed: " + disposables.isDisposed());
        
        // Let the program run for another 5 seconds to verify no emissions
        Thread.sleep(5000);
    }
}
