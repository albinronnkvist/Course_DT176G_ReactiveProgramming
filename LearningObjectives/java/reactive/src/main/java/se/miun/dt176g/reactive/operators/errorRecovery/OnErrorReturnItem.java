package se.miun.dt176g.reactive.operators.errorRecovery;

import io.reactivex.rxjava3.core.Observable;

public class OnErrorReturnItem {
    public void run() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorReturnItem(-1)
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
