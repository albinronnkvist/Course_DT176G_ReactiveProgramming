package se.miun.dt176g.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BasicFlowTwo {
    public void run() {

        // Observable. This will emit the data.
        Observable<String> database = Observable.just("1", "2", "3", "4");

        // Observer that listens to emissions from the Observable.
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Subscribed to the observable.");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Received: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("All data received.");
            }
        };

        // Subscribe the observer to the observable, specifying threading.
        database.subscribeOn(Schedulers.io()) // Observable runs on a background I/O thread.
                .observeOn(Schedulers.computation()) // Observer processes data on a computation thread.
                .subscribe(observer); // Subscribe the observer.
    }
}
