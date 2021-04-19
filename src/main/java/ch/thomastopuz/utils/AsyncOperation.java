package ch.thomastopuz.utils;

import org.springframework.stereotype.Component;

/**
 * Injectable for executing callbacks after a timer
 */
@Component
public class AsyncOperation {
    /**
     * Method to execute functions after a delay, simulating setTimeout in javascript
     * @param runnable the runnable to execute
     * @param delay delay in milliseconds
     */
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
