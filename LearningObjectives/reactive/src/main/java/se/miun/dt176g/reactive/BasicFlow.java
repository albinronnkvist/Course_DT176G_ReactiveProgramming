package se.miun.dt176g.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BasicFlow {
    // Manage multiple Disposable objects
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void run() {
        // Create Observable
        var observable = Observable.just("hello", "world");
        
        
        // Create Observer
        var observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                // Save the subscription
                compositeDisposable.add(d);
                
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
            
            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }
            
            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        
        observable
            .map(s -> s.toUpperCase()) // Transform
            .subscribe(observer); // Subscribe
    }
}
