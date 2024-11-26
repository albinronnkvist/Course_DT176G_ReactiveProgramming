package se.miun.dt176g.reactive.observables.factoryMethods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import io.reactivex.rxjava3.core.Observable;

public class FromFuture {
    public void run() throws InterruptedException, ExecutionException {
        Future<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate some computation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, RxJava!";
        });

        // Convert Future to Observable
        Observable.fromFuture(future)
                .map(String::length)
                .subscribe(
                        result -> System.out.println("Length: " + result),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );

        System.out.println("Future completed, it blocked me...");
    }
}
