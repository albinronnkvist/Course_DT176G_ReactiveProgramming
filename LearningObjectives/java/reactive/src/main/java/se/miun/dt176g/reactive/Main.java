package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.combining.Group;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Group();
            runner.runGroupByWithFlatMapSingle();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}