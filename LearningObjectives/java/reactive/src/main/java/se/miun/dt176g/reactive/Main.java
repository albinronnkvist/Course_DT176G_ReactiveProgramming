package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.collection.Collect;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Collect();
            runner.runWithCustomCollection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}