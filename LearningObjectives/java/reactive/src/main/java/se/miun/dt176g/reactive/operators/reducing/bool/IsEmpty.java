package se.miun.dt176g.reactive.operators.reducing.bool;

import io.reactivex.rxjava3.core.Observable;

public class IsEmpty {
    public void runEmpty() {
        Observable.just("One", "Two", "Three")
                .filter(s -> s.contains("z"))
                .isEmpty()
                .subscribe(s -> System.out.println("Is empty: " + s));
    }

    public void runNotEmpty() {
        Observable.just("One", "Twoz", "Three")
                .filter(s -> s.contains("z"))
                .isEmpty()
                .subscribe(s -> System.out.println("Is empty: " + s));
    }
}
