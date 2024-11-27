package se.miun.dt176g.reactive.operators.errorRecovery;

import io.reactivex.rxjava3.core.Observable;

public class OnErrorResumeWith {
    public void run() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorResumeWith(Observable.just(-1).repeat(3))
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

    public void runWithSilentTermination() {
        Observable.just(5, 2, 1, 0, 3)
                .map(i -> 10 / i)
                .onErrorResumeWith(Observable.empty())
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

    public void runWithDynamicFallback() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorResumeNext((Throwable e) -> e instanceof ArithmeticException
                        ? Observable.just(-1).repeat(3)
                        : Observable.just(-2))
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
