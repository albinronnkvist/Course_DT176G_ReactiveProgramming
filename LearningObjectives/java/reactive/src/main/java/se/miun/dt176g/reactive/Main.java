package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.reducing.bool.SequenceEqual;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new SequenceEqual();
            runner.runEmpty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}