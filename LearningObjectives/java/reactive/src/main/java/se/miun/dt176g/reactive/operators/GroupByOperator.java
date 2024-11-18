package se.miun.dt176g.reactive.operators;

import io.reactivex.rxjava3.core.Observable;

public class GroupByOperator {
    public void run() {
        final StringBuilder[] EVEN = {new StringBuilder()};
        final StringBuilder[] ODD = {new StringBuilder()};

        // Observable emitting numbers from 0 to 10
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
        System.out.println("ODD: " + ODD[0]);  // Should print: 13579
    }
}
