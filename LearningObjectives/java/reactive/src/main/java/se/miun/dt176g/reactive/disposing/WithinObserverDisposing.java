package se.miun.dt176g.reactive.disposing;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class WithinObserverDisposing {
    public void run() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);

        Observer<Integer> myObserver = new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable; // Capture the Disposable
                System.out.println("Subscribed!");
                System.out.println("Is disposed: " + disposable.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Received: " + value);
                if (value == 3) {
                    System.out.println("Disposing after receiving 3...");
                    disposable.dispose(); // Stop receiving emissions
                    System.out.println("Is disposed: " + disposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
            }
        };

        source.subscribe(myObserver);
    }
}
