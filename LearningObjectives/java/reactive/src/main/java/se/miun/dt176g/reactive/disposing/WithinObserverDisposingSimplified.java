package se.miun.dt176g.reactive.disposing;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.ResourceObserver;

public class WithinObserverDisposingSimplified {
    public void run() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        ResourceObserver<Long> myObserver = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long value) {
                System.out.println("Received: " + value);
                if (value == 3) {
                    System.out.println("Disposing after receiving 3...");
                    dispose(); // Dispose using ResourceObserver's method
                }
                System.out.println("Is disposed: " + isDisposed());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
            }
        };

        // Possible to fetech Disposal object as such: var disposable = source.subscribeWith(myObserver);
        source.subscribeWith(myObserver);

        // Keep the main thread alive to ensure no more emissions
        Thread.sleep(5000);
    }
}
