package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using a correct version of the double-checked locking idiom. Thread safe but
 * error-prone and often unnecessary.
 */
@ThreadSafe
public class SafeDoubleCheckedLazySingletonDate {
    private static volatile Date epoch;

    // Disallow instantiation.
    private SafeDoubleCheckedLazySingletonDate() {
    }

    public static Date getEpoch() {
        if (epoch == null) {
            synchronized (SafeDoubleCheckedLazySingletonDate.class) {
                if (epoch == null) {
                    epoch = Date.from(Instant.EPOCH);
                }
            }
        }
        return new Date(epoch.getTime());
    }
}

