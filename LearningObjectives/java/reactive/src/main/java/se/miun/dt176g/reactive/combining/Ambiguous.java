package se.miun.dt176g.reactive.combining;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Ambiguous {
    public void run() throws InterruptedException {
        Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> "Source1: " + (l + 1) + " seconds");

        // Source 2: emits every 300 milliseconds
        Observable<String> src2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> "Source2: " + ((l + 1) * 300) + " milliseconds");

        // Only the first Observable to emit will continue
        Observable.amb(Arrays.asList(src1, src2))
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        Thread.sleep(5000);
    }
}
