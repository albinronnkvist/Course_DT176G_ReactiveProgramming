package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.combining.CombineLatest;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new CombineLatest();
            runner.runWithLatestFrom();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}