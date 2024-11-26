package se.miun.dt176g.reactive.observers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DefaultObserver {
    public void run() {
        var source = Observable.just("Alpha", "Beta", "Gamma");
        
        var observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Subscribed!");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("RECEIVED: " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error occurred: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("All items emitted!");
            }
        };
        
        source.map(String::length)
              .filter(i -> i >= 5)
              .subscribe(observer);
    }

    public void runWithLambda() {
        Observable.just("Alpha", "Beta", "Gamma")
            .map(String::length)
            .filter(i -> i >= 5)
            .subscribe(
                value -> System.out.println("RECEIVED: " + value),
                throwable -> System.err.println("Error occurred: " + throwable.getMessage()),
                () -> System.out.println("All items emitted!")
            );
    }

}
