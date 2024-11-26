package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.Create;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Create();
            runner.runWithManualOperators();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}