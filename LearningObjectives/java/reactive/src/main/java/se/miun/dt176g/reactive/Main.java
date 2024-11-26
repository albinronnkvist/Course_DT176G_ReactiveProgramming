package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.Never;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Never();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}