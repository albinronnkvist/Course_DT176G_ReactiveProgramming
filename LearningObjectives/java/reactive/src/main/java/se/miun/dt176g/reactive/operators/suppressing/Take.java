package se.miun.dt176g.reactive.operators.suppressing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Take {
    public void runWithCount() {
        Observable.range(1, 10)
                .take(5)
                .subscribe(System.out::println);
    }

    public void runWithTime() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss:SSS");
        System.out.println(LocalDateTime.now().format(formatter));

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println(LocalDateTime.now()
                    .format(formatter) + " RECEIVED: " + i));

        Thread.sleep(5000);
    }
}
