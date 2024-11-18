package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.operators.GroupByOperator;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new GroupByOperator();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}