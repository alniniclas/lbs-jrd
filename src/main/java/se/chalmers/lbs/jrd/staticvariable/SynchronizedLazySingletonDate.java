package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using {@code .class}.
 */
@ThreadSafe
public class SynchronizedLazySingletonDate {
    @GuardedBy("SynchronizedLazySingletonDate.class")
    private static Date epoch;

    // Disallow instantiation.
    private SynchronizedLazySingletonDate() {
    }

    public static synchronized Date getEpoch() {
        if (epoch == null) {
            epoch = Date.from(Instant.EPOCH);
        }
        return new Date(epoch.getTime());
    }
}

