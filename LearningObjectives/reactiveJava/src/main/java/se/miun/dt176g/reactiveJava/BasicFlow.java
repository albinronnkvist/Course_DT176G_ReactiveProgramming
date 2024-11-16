package se.miun.dt176g.reactiveJava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class BasicFlow {
    // Manage multiple Disposable objects (only necessary for multiple observers)
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void run() {
        // Create Observable
        var observable = Observable.just("hello", "world");
        
        
        // Create Observer
        var observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
            
            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
                compositeDisposable.dispose();
            }
            
            @Override
            public void onComplete() {
                System.out.println("onComplete");
                compositeDisposable.dispose();
            }
        };
        
        compositeDisposable.add(
            observable
                .map(String::toUpperCase) // Transform each item
                .subscribeWith(observer)  // Subscribe and add the subscription to CompositeDisposable
        );
    }
}
