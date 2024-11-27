package se.miun.dt176g.reactive.operators.reducing.bool;

import java.time.LocalDate;

import io.reactivex.rxjava3.core.Observable;

public class Any {
    public void runSatisfied() {
        Observable.just("2016-01-01", "2016-05-02", "2016-09-12", "2016-04-03")
                .map(LocalDate::parse)
                .any(dt -> dt.getMonthValue() >= 6)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runNotSatisfied() {
        Observable.just("2016-01-01", "2016-03-15", "2016-04-10")
                .map(LocalDate::parse)
                .any(dt -> dt.getMonthValue() >= 6)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public void runEmpty() {
        Observable.<String>empty()
                .any(s -> s.length() > 0)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
