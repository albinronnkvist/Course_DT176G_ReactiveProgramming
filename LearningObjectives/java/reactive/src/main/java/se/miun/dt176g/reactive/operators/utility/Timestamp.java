package se.miun.dt176g.reactive.operators.utility;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Timestamp {
    public void run() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3) // Take only the first 3 emissions
                .timestamp(TimeUnit.MILLISECONDS)
                .subscribe(i -> System.out.println(
                        "Received: Time = " + i.time() + " ms, Value = " + i.value()));

        Thread.sleep(4000); // Keep the program alive to observe emissions
    }
}
