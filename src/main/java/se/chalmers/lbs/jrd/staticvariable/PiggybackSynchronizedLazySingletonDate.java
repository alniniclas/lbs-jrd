package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class PiggybackSynchronizedLazySingletonDate {
    @GuardedBy("PiggybackSynchronizedLazySingletonDate.class")
    private static boolean initialized = false;
    private static Date epoch;

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
