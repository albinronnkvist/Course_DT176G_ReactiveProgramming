package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.Interval;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Interval();
            runner.runWithMultipleObserversHot();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}