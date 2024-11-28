package se.miun.dt176g.reactive.combining;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Zip {
    public void run() {
        Observable<String> names = Observable.fromArray("Alice", "Bob", "Charlie", "David", "Eve");
        Observable<Integer> ages = Observable.fromArray(25, 30, 35, 40, 45);

        Observable
                .zip(names, ages, (name, age) -> name + " is " + age + " years old.")
                .filter(data -> data.contains("Charlie"))
                .map(data -> data.toUpperCase())
                .subscribe(data -> System.out.println(data));
    }

    public void runSlow() throws InterruptedException {
        Observable<String> strings = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Observable.zip(strings, seconds, (string, second) -> string)
                .subscribe(s -> System.out.println("Received " + s + " at " + LocalTime.now()));

        Thread.sleep(5000);
    }
}
