package se.miun.dt176g.reactive.operators.conditional;

import io.reactivex.rxjava3.core.Observable;

public class SwitchIfEmpty {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma")
            .filter(s -> s.startsWith("Z"))
            .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
            .subscribe(i -> System.out.println("RECEIVED: " + i),
                       e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
