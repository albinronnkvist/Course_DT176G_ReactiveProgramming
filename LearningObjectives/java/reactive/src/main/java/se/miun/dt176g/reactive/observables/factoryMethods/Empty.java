package se.miun.dt176g.reactive.observables.factoryMethods;

import io.reactivex.rxjava3.core.Observable;

public class Empty {
    public void run() {
        Observable<String> empty = Observable.empty();

        empty.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!"));
    }

    public void runSkip() {
        Observable<String> source = Observable.just("Apple", "Banana", "Orange");

        source.flatMap(fruit -> {
            if (fruit.equals("Banana")) {
                return Observable.empty();
            } else {
                return Observable.just(fruit);
            }
        })
        .subscribe(
            System.out::println, 
            Throwable::printStackTrace, () -> System.out.println("Done!")
        );
    }
}
