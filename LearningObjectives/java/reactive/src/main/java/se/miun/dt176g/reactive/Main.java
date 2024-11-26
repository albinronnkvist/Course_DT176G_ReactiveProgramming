package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.disposing.CompositeDisposing;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new CompositeDisposing();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}