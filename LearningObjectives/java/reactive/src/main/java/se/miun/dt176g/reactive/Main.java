package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.observables.Connectable;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Connectable();
            runner.runWithAutoConnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}