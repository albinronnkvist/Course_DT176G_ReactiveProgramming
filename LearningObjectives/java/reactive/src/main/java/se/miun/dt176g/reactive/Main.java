package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.MaybeObservable;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new MaybeObservable();
            runner.runWithoutValue();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}