package ch.thomastopuz.utils;

import org.springframework.stereotype.Component;

@Component // let spring boot inject this class for me
public class AsyncOperation {
    public void await(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}
