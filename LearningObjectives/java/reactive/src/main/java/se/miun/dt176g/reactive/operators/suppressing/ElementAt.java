package se.miun.dt176g.reactive.operators.suppressing;

import io.reactivex.rxjava3.core.Observable;

public class ElementAt {
    public void run() {
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma")
                .elementAt(3)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("ERROR: " + e),
                        () -> System.out.println("NO EMISSION"));
    }

    public void runWithIndexOutOfBounds() {
        Observable.just("Alpha", "Beta", "Zeta")
                .elementAt(5)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("ERROR: " + e),
                        () -> System.out.println("NO EMISSION"));
    }
}
