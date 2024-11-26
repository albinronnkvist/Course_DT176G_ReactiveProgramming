package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.Range;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Range();
            runner.runWithLong();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}