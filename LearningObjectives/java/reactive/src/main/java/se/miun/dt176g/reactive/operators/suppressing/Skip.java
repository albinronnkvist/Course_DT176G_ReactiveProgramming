package se.miun.dt176g.reactive.operators.suppressing;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Skip {
    public void runWithCount() {
        Observable.range(1, 10)
            .skip(5)
            .subscribe(System.out::println);
    }

    public void runWithTime() throws InterruptedException {
        Observable.interval(300, TimeUnit.MILLISECONDS)
            .skip(2, TimeUnit.SECONDS)
            .subscribe(i -> System.out.println("RECEIVED: " + i));
        Thread.sleep(5000);
    }
}
