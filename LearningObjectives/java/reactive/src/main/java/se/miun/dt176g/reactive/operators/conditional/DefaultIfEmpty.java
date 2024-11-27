package se.miun.dt176g.reactive.operators.conditional;

import io.reactivex.rxjava3.core.Observable;

public class DefaultIfEmpty {
    public void run() {
        Observable<String> emptyObservable = Observable.empty();

        emptyObservable.defaultIfEmpty("Default Value")
                .subscribe(System.out::println);
    }
}
