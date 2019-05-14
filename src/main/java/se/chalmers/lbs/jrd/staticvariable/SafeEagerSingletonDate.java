package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using eager initialization. Thread safe as long as mutable {@code Date} does not
 * escape.
 */
@ThreadSafe
public class SafeEagerSingletonDate {
    private static final Date EPOCH = Date.from(Instant.EPOCH);

    // Disallow instantiation.
    private SafeEagerSingletonDate() {
    }

    public static Date getEpoch() {
        return new Date(EPOCH.getTime());
    }
}

