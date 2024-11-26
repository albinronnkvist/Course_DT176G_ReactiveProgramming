package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.CompletableObservable;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new CompletableObservable();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}