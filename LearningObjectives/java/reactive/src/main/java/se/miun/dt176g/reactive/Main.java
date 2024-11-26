package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.SingleObservable;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new SingleObservable();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}