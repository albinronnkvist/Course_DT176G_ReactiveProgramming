package se.miun.dt176g.reactive.operators.action;

import io.reactivex.rxjava3.core.Observable;

public class DoFinally {
    public void run() {
        Observable.just("One", "Two", "Three")
                .doFinally(() -> System.out.println("doFinally!"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate!"))
                .subscribe(i -> System.out.println("Received: " + i));
    }
}
