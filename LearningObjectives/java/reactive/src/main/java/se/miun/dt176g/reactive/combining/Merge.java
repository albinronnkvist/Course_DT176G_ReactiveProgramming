package se.miun.dt176g.reactive.combining;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Merge {
    public void runObservableMerge() {
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");

        Observable.merge(src1, src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public void runObservableMergeWithInfiniteStreams() throws InterruptedException {
        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> "Source1: " + (l + 1) + " seconds");

        Observable<String> src2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> "Source2: " + ((l + 1) * 300) + " milliseconds");

        Observable.merge(src1, src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        Thread.sleep(5000);
    }

    public void runObservableMergeWithIterable() {
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");
        Observable<String> src3 = Observable.just("Theta", "Iota");
        Observable<String> src4 = Observable.just("Kappa", "Lambda");
        Observable<String> src5 = Observable.just("Mu", "Nu");
        List<Observable<String>> sources = Arrays.asList(
                src1, src2, src3, src4, src5);

        Observable.merge(sources)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public void runObservableMergeArray() {
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Gamma", "Delta");
        Observable<String> src3 = Observable.just("Epsilon", "Zeta");
        Observable<String> src4 = Observable.just("Eta", "Theta");
        Observable<String> src5 = Observable.just("Iota", "Kappa");
        Observable.mergeArray(src1, src2, src3, src4, src5)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public void runMergeWith() throws InterruptedException {
        Observable<String> src1 = Observable.just("Alpha", "Beta");
        Observable<String> src2 = Observable.just("Zeta", "Eta");
        
        src1.mergeWith(src2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
