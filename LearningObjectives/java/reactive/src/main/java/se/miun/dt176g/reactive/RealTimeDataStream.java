package se.miun.dt176g.reactive;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class RealTimeDataStream  {
    public void run() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Observable<Long> timer = Observable.interval(1, TimeUnit.SECONDS);

        // Subscribe to the timer stream
        timer.subscribe(n -> System.out.println(n),
                throwable -> latch.countDown(),
                latch::countDown);

        latch.await(); // Keeps the main thread alive
    }
}
