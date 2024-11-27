package se.miun.dt176g.reactive.operators.errorRecovery;

import io.reactivex.rxjava3.core.Observable;

public class OnErrorReturn {
    public void run() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorReturn(e -> e instanceof ArithmeticException ? -1 : 0)
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
