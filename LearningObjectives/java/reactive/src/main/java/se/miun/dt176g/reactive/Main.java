package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.combining.Zip;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Zip();
            runner.runSlow();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}