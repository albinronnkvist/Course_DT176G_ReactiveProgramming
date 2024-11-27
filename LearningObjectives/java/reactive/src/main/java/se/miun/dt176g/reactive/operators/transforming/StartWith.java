package se.miun.dt176g.reactive.operators.transforming;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class StartWith {
    public void runWithItem() {
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");

        menu.startWithItem("COFFEE SHOP MENU")
                .subscribe(System.out::println);
    }

    public void runWithArray() {
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");

        menu.startWithArray("COFFEE SHOP MENU", "----------------")
                .subscribe(System.out::println);
    }

    public void runWithIterable() {
        List<String> header = Arrays.asList("COFFEE SHOP MENU", "----------------");

        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");

        menu.startWithIterable(header)
                .subscribe(System.out::println);
    }
}
