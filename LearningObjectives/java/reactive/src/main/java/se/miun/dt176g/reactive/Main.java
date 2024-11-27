package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.suppressing.ElementAt;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new ElementAt();
            runner.runWithIndexOutOfBounds();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}