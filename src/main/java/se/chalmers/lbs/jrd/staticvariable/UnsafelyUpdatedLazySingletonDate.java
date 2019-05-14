package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using an almost correct version of the double-checked locking idiom but modifies the
 * created {@code Date} afterwards. Not actually thread safe.
 */
@ThreadSafe
public class UnsafelyUpdatedLazySingletonDate {
    @GuardedBy("UnsafelyUpdatedLazySingletonDate.class")
    private static volatile Date epoch;

    // Disallow instantiation.
    private UnsafelyUpdatedLazySingletonDate() {
    }

    public static Date getEpoch() {
        if (epoch == null) {
            synchronized (UnsafelyUpdatedLazySingletonDate.class) {
                if (epoch == null) {
                    epoch = new Date();
                    epoch.setTime(Instant.EPOCH.toEpochMilli());
                }
            }
        }
        return new Date(epoch.getTime());
    }
}
