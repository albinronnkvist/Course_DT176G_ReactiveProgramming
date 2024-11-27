package se.miun.dt176g.reactive.operators.reducing.bool;

import io.reactivex.rxjava3.core.Observable;

public class SequenceEqual {
    public void run() {
        Observable<String> obs1 = Observable.just("One", "Two", "Three");
        Observable<String> obs2 = Observable.just("One", "Two", "Three");
        Observable<String> obs3 = Observable.just("Two", "One", "Three");
        Observable<String> obs4 = Observable.just("One", "Two");

        Observable.sequenceEqual(obs1, obs2)
                .subscribe(s -> System.out.println("Received: " + s)); // true

        Observable.sequenceEqual(obs1, obs3)
                .subscribe(s -> System.out.println("Received: " + s)); // false

        Observable.sequenceEqual(obs1, obs4)
                .subscribe(s -> System.out.println("Received: " + s)); // false
    }

    public void runEmpty() {
        Observable<String> obs1 = Observable.empty();
        Observable<String> obs2 = Observable.empty();

        Observable.sequenceEqual(obs1, obs2)
                .subscribe(s -> System.out.println("Received: " + s)); // true
    }
}
