package se.miun.dt176g.reactive.operators.transforming;

import java.util.Comparator;

import io.reactivex.rxjava3.core.Observable;

public class Sorted {
    public void run() {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted()
                .subscribe(System.out::print);
    }

    public void runReverse() {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::print);
    }

    public void runWithCustomComparator() {
        Observable.just("aaaC", "aaaA", "aaaB")
                .sorted((s1, s2) -> Character.compare(s1.charAt(s1.length() - 1), s2.charAt(s2.length() - 1)))
                .subscribe(System.out::println);
    }
}
