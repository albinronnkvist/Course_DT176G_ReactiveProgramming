package se.miun.dt176g.reactive.operators.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Delay {
    public void run() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
        System.out.println(LocalDateTime.now().format(formatter));

        Observable.just("Alpha", "Beta", "Gamma")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now().format(formatter) + " Received: " + s));

        Thread.sleep(5000); // Keep the application alive to observe emissions
    }

    public void runWithObservable() throws InterruptedException {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Long> delayNotifier = Observable.timer(2, TimeUnit.SECONDS);

        source.delay(item -> delayNotifier)
                .subscribe(System.out::println);

        Thread.sleep(5000); // Keep the application alive to observe emissions
    }

    public void runWithError() throws InterruptedException {
        Observable.just(1, 0, 2)
                .map(i -> 10 / i) // Will throw ArithmeticException
                .delay(3, TimeUnit.SECONDS, true) // Delay errors as well
                .subscribe(
                        System.out::println,
                        e -> System.out.println("Error: " + e));

        Thread.sleep(5000);
    }
}
