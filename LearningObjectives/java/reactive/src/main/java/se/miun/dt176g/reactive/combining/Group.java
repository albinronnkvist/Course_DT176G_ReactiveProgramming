package se.miun.dt176g.reactive.combining;

import io.reactivex.rxjava3.core.Observable;

public class Group {
    public void runGroupBy() {
        final StringBuilder[] EVEN = { new StringBuilder() };
        final StringBuilder[] ODD = { new StringBuilder() };

        Observable.range(0, 11)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD") // Group numbers by "EVEN" or "ODD"
                .subscribe(group -> group.subscribe(number -> {
                    if (group.getKey().equals("EVEN")) {
                        EVEN[0].append(number); // Append number to EVEN group
                    } else {
                        ODD[0].append(number); // Append number to ODD group
                    }
                }));

        System.out.println("EVEN: " + EVEN[0]); // Should print: 0246810
        System.out.println("ODD: " + ODD[0]); // Should print: 13579
    }

    public void runGroupBy2() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(s -> s.length())
                .subscribe(group -> {
                    System.out.println("Group Key: " + group.getKey());
                    group.subscribe(value -> System.out.println("  Value: " + value));
                });
    }

    public void runGroupByWithFlatMapSingle() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(s -> s.length())
                .flatMapSingle(group -> group.toList())
                .subscribe(System.out::println);
    }
}
