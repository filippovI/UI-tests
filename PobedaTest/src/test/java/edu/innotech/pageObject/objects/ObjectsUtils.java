package edu.innotech.pageObject.objects;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class ObjectsUtils {
    public static void waiting(Callable<Boolean> condition, Duration duration) {
        await().alias("Awaitility waiting")
                .atMost(duration)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .ignoreNoExceptions()
                .until(condition);
    }
}
