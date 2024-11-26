package se.miun.dt176g.reactive.observables.factoryMethods;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Interval {
    public void run() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(
                s -> System.out.println(LocalDateTime.now().getSecond() + " " + s + " Mississippi"),
                throwable -> latch.countDown(),
                latch::countDown
            );

        latch.await(); // Keeps the main thread alive
    }

    public void runWithMultipleObserversCold() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        
        var source = Observable.interval(1, TimeUnit.SECONDS);

        source.subscribe(
                s -> System.out.println(LocalDateTime.now().getSecond() + " " + s + " Observer 1"),
                throwable -> latch.countDown(),
                latch::countDown
            );

        Thread.sleep(3000);

        source.subscribe(
                s -> System.out.println(LocalDateTime.now().getSecond() + " " + s + " Observer 2"),
                throwable -> latch.countDown(),
                latch::countDown
            );

        latch.await(); // Keeps the main thread alive
    }

    public void runWithMultipleObserversHot() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        
        var source = Observable.interval(1, TimeUnit.SECONDS).publish(); 

        source.subscribe(
                s -> System.out.println(LocalDateTime.now().getSecond() + " " + s + " Observer 1"),
                throwable -> latch.countDown(),
                latch::countDown
            );

        source.connect();
        Thread.sleep(3000);

        source.subscribe(
                s -> System.out.println(LocalDateTime.now().getSecond() + " " + s + " Observer 2"),
                throwable -> latch.countDown(),
                latch::countDown
            );

        latch.await(); // Keeps the main thread alive
    }
}
