package se.miun.dt176g.reactive;

public class Main {

    public static void main(String[] args) {
        try {
            var runner = new Callback();
            runner.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}