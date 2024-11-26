package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.HotVsColdObservables;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new HotVsColdObservables();
            runner.runHot();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}