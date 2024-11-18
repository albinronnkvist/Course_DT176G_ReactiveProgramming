package se.miun.dt176g.reactive;

import java.util.concurrent.CountDownLatch;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AsynchronousDataStream {
    public void run() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Observable<String> data = Observable.fromCallable(() -> {
            Thread.sleep(3000);
            return "Data Loaded";
        });

        data.subscribeOn(Schedulers.io())
                .subscribe(d -> System.out.println(d),
                throwable -> latch.countDown(),
                latch::countDown);

        System.out.println("Doing other things...");

        latch.await(); // Keeps the main thread alive
    }
}
