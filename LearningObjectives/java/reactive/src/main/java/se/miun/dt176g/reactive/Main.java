package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.combining.Concatenate;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Concatenate();
            runner.runWithConcatMap();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}