package se.miun.dt176g.reactive.operators.conditional;

import io.reactivex.rxjava3.core.Observable;

public class TakeWhile {
    public void run() {
        Observable<Integer> numbers = Observable.range(1, 10);

        numbers.takeWhile(number -> number < 5)
                .subscribe(System.out::println);
    }
}
