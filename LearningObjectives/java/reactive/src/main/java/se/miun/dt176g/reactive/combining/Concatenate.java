package se.miun.dt176g.reactive.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Concatenate {
    // Observable factory methods
    public void runWithObservableConcatFinite() {
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");

        Observable.concat(src1, src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public void runWithObservableConcatManageInfinite() throws InterruptedException {
        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> "Source1: " + (l + 1) + " seconds");

        Observable<String> src2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> "Source2: " + ((l + 1) * 300) + " milliseconds");

        Observable.concat(src1, src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        Thread.sleep(5000);
    }

    // Operators
    public void runWithConcatWith() {
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");

        src1.concatWith(src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public void runWithConcatMap() {
        Observable.just("Alpha", "Beta", "Gamma")
                .concatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }
}
