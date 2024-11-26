package se.miun.dt176g.reactive.observables;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class HotVsColdObservables {
    public void runCold() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        source.subscribe(s -> System.out.println("Observer 1: " + s));

        source.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(s -> System.out.println("Observer 2: " + s));
    }

    public void runHot() {
        PublishSubject<String> hotObservable = PublishSubject.create();

        // First Observer subscribes
        hotObservable.subscribe(item -> System.out.println("Observer 1 received: " + item));

        // Emit some items
        hotObservable.onNext("Alpha");
        hotObservable.onNext("Beta");

        // Second Observer subscribes
        hotObservable.subscribe(item -> System.out.println("Observer 2 received: " + item));

        // Emit more items
        hotObservable.onNext("Gamma");
        hotObservable.onNext("Delta");
    }
}
