package se.miun.dt176g.reactive.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class CombineLatest {
    public void runObservableCombineLatest() throws InterruptedException {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        Observable.combineLatest(
                source1,
                source2,
                (l1, l2) -> "SOURCE 1: " + l1 + " SOURCE 2: " + l2).subscribe(System.out::println);

        Thread.sleep(3000);
    }

    public void runWithLatestFrom() throws InterruptedException {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        source2.withLatestFrom(source1, (l1, l2) -> "SOURCE 2: " + l1 + " SOURCE 1: " + l2)
                .subscribe(System.out::println);

        Thread.sleep(3000);
    }
}
