package se.miun.dt176g.reactive.operators.collection;

import java.util.HashSet;

import io.reactivex.rxjava3.core.Observable;

public class Collect {
    public void run() {
        Observable.just("Alpha", "Beta", "Gamma", "Beta")
                .collect(HashSet<String>::new, HashSet::add)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runWithCustomCollection() {
        Observable.just("Alpha", "Beta", "Gamma")
                .collect(StringBuilder::new, (sb, s) -> sb.append(s).append(", "))
                .map(sb -> sb.substring(0, sb.length() - 2)) // Remove the trailing comma and space
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
