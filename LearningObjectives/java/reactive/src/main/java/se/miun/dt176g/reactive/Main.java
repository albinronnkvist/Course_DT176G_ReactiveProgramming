package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.utility.TimeInterval;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new TimeInterval();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}