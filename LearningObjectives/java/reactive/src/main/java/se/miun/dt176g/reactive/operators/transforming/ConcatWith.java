package se.miun.dt176g.reactive.operators.transforming;

import io.reactivex.rxjava3.core.Observable;

public class ConcatWith {
    public void run() {
        Observable<String> header = Observable.just("COFFEE SHOP MENU", "----------------");
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");

        header.concatWith(menu)
                .subscribe(System.out::println);
    }
}
