package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.combining.FlatMap;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new FlatMap();
            runner.runWithCombiner();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}