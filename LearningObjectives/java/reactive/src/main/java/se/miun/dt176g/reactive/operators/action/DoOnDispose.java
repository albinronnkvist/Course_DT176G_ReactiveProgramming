package se.miun.dt176g.reactive.operators.action;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class DoOnDispose {
    public void run() throws InterruptedException {
        Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        Thread.sleep(3000); // Allow emissions for 3 seconds
        disp.dispose(); // Manually dispose of the subscription
        Thread.sleep(3000); // Observe that no emissions occur after disposal
    }
}
