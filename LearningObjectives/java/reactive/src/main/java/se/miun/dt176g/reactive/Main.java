package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.Defer;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Defer();
            runner.runWithDefer();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}