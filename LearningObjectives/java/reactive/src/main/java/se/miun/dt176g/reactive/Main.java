package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.factoryMethods.Empty;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Empty();
            runner.runSkip();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}