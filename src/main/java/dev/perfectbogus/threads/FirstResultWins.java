package dev.perfectbogus.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstResultWins {

    private final static int NUMBER_TASKS = 3;

    public static String race() throws Exception {
        try (ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_TASKS)) {

            List<Callable<String>> tasks = new ArrayList<>(NUMBER_TASKS);

            tasks.add(() -> {
                Thread.sleep(3000);
                return "A";
            });

            tasks.add(() -> {
                Thread.sleep(1000);
                return "B";
            });

            tasks.add(() -> {
                Thread.sleep(2000);
                return "C";
            });

            return executorService.invokeAny(tasks);
        }
    }
}
