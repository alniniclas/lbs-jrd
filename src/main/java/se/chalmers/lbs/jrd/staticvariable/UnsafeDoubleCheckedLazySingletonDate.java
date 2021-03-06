package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using the double-checked locking idiom. May cause not-fully-initialized object to
 * become visible due to potential reordering by compiler. Not actually thread safe.
 */
@ThreadSafe
public class UnsafeDoubleCheckedLazySingletonDate {
    private static Date epoch;

    // Disallow instantiation.
    private UnsafeDoubleCheckedLazySingletonDate() {
    }

    public static Date getEpoch() {
        if (epoch == null) {
            synchronized (UnsafeDoubleCheckedLazySingletonDate.class) {
                if (epoch == null) {
                    epoch = Date.from(Instant.EPOCH);
                }
            }
        }
        return new Date(epoch.getTime());
    }
}

