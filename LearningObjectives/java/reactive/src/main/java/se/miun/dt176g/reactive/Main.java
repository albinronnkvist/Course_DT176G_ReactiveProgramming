package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.FromCallable;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new FromCallable();
            runner.runWithoutFromCallable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}