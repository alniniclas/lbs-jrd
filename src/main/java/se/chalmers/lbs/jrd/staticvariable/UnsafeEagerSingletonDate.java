package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using eager initialization. Allows mutable {@code Date} to escape. Not actually
 * thread safe.
 */
@ThreadSafe
public class UnsafeEagerSingletonDate {
    private static final Date EPOCH = Date.from(Instant.EPOCH);

    // Disallow instantiation.
    private UnsafeEagerSingletonDate() {
    }

    public static Date getEpoch() {
        return EPOCH;
    }
}

