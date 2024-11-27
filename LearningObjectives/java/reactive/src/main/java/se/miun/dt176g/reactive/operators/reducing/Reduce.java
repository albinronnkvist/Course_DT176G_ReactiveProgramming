package se.miun.dt176g.reactive.operators.reducing;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Reduce {
    public void run() {
        Observable.just(5, 3, 7)
                .reduce((total, i) -> total + i)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithSeed() {
        Observable.just(1, 2, 3)
                .reduce(10, (total, i) -> total + i)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithUnsafeMutableSeed() {
        List<Integer> list = new ArrayList<>();
        Observable.just(1, 2, 3)
                .reduce(list, (acc, i) -> {
                    acc.add(i);
                    return acc;
                })
                .subscribe(System.out::println);
    }
}
