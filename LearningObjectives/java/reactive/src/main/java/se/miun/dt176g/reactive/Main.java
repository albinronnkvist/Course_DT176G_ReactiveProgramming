package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.action.DoFinally;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new DoFinally();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}