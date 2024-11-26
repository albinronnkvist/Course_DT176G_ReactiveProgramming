package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observers.DefaultObserver;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new DefaultObserver();
            runner.runWithLambda();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}