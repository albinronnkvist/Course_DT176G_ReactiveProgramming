package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.combining.Ambiguous;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Ambiguous();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}