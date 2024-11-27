package se.miun.dt176g.reactive.operators.errorRecovery;

import io.reactivex.rxjava3.core.Observable;

public class ErrorWithoutTermination {
    public void run() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> {
                    try {
                        return 10 / i;
                    } catch (ArithmeticException e) {
                        return -1; // Replace the value causing the error
                    }
                })
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
