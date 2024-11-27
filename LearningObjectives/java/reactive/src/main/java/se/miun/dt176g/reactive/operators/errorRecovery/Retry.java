package se.miun.dt176g.reactive.operators.errorRecovery;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Retry {
    public void runInfinite() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .retry()
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

    public void runLimited() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .retry(2)
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

    public void runWithBiPredicate() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .retry((retryCount, e) -> retryCount < 3 && e instanceof ArithmeticException)
                .subscribe(
                        i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

    public void runWithDelay() throws InterruptedException {
        Observable.just(5, 2, 4, 0, 3)
            .map(i -> 10 / i)
            .retryWhen(errors ->
                errors.zipWith(Observable.range(1, 3), (err, retryCount) -> retryCount)
                      .flatMap(retryCount -> {
                          System.out.println("Retrying in " + retryCount + " second(s)...");
                          return Observable.timer(retryCount, TimeUnit.SECONDS);
                      })
            )
            .subscribe(
                i -> System.out.println("RECEIVED: " + i),
                e -> System.out.println("RECEIVED ERROR: " + e)
            );

        Thread.sleep(5000);
    }
}
