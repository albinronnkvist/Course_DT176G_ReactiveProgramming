package se.miun.dt176g.reactive.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class FlatMap {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }

    public void runWithItemBreakdown() {
        Observable.just("521934/2342/FOXTROT", "21962/12112/TANGO/78886")
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .filter(s -> s.matches("[0-9]+")) // regex to filter integers
                .map(Integer::valueOf)
                .subscribe(System.out::println);
    }

    public void runWithDynamicConcurrentInfinite() throws InterruptedException {
        Observable.just(2, 0, 3)
                .flatMap(i -> {
                    if (i == 0) {
                        return Observable.empty(); // Skip value 0
                    }
                    return Observable.interval(i, TimeUnit.SECONDS)
                            .map(l -> i + "s interval: " + ((l + 1) * i) + " seconds elapsed");
                })
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }

    public void runWithCombiner() {
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(
                        s -> Observable.fromArray(s.split("")), // Mapper: Split string into letters
                        (originalString, letter) -> originalString + "-" + letter // Combiner
                )
                .subscribe(System.out::println);
    }
}
