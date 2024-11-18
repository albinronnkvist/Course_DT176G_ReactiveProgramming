package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.ScanOperator;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new ScanOperator();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}