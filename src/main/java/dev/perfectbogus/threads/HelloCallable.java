package dev.perfectbogus.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HelloCallable {

    public static String greet(String name) throws Exception {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Future<String> future = executor.submit(() -> "Hello from " + name + " on " + Thread.currentThread().getName());
            return future.get();
        }
    }
}
