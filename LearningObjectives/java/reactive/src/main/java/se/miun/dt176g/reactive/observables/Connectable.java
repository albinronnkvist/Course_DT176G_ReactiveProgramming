package se.miun.dt176g.reactive.observables;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class Connectable {
    public void run() {
        // Create a cold Observable and convert it to a ConnectableObservable
        ConnectableObservable<String> source = Observable.just("Alpha", "Beta", "Gamma").publish();

        // Set up Observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));

        // Set up Observer 2 with a transformation
        source.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        // Start emissions
        source.connect();
    }

    public void runWithAutoConnect() {
        // Create a cold Observable and convert it to a ConnectableObservable
        var source = Observable
            .just("Alpha", "Beta", "Gamma")
            .publish()
            .autoConnect(2);

        // Set up Observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));

        // Set up Observer 2 with a transformation
        source.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));
    }
}
