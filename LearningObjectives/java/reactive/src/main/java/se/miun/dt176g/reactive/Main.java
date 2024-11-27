package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.transforming.Scan;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Scan();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}