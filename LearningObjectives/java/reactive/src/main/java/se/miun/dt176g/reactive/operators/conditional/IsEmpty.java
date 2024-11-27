package se.miun.dt176g.reactive.operators.conditional;

import io.reactivex.rxjava3.core.Observable;

public class IsEmpty {
    public void run() {
        Observable<Integer> empty = Observable.empty();

        empty.isEmpty()
            .subscribe(System.out::println); // Prints true
    }
}
