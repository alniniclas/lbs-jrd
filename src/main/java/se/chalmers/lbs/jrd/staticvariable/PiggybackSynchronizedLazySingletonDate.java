package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes by piggybacking on synchronization of different variable. Should be thread safe but
 * may be error-prone and confusing.
 */
@ThreadSafe
public class PiggybackSynchronizedLazySingletonDate {
    @GuardedBy("PiggybackSynchronizedLazySingletonDate.class")
    private static boolean initialized = false;
    private static Date epoch;

    // Disallow instantiation.
    private PiggybackSynchronizedLazySingletonDate() {
    }

    public static synchronized Date getEpoch() {
        synchronized (PiggybackSynchronizedLazySingletonDate.class) {
            if (!initialized) {
                epoch = Date.from(Instant.EPOCH);
            }
            initialized = true;
        }
        return new Date(epoch.getTime());
    }
}
