package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.errorRecovery.Retry;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Retry();
            runner.runWithDelay();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}