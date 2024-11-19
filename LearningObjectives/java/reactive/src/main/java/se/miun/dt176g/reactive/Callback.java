package se.miun.dt176g.reactive;

import se.miun.dt176g.reactive.other.TaskCallback;

public class Callback {
    public void run() {
        runWithLambda();
        runWithAnonymousClass();
    }

    private void runWithLambda() {
        performTask(result -> System.out.println("Main Program: Callback received - " + result));
    }

    private void runWithAnonymousClass() {
        performTask(new TaskCallback() {
            @Override
            public void onComplete(String result) {
                System.out.println("Main Program: Callback received - " + result);
            }
        });
    }

    private void performTask(TaskCallback callback) {
        System.out.println("Worker: Performing a long task...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Task completed, notify the callback
        callback.onComplete("Task completed successfully!");
    }
}
