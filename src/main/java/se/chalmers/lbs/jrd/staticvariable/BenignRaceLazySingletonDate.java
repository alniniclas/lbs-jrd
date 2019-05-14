package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using benign race. Has a data race but should still be thread safe.
 */
@ThreadSafe
public class BenignRaceLazySingletonDate {
    private static Instant epoch;

    // Disallow instantiation.
    private BenignRaceLazySingletonDate() {
    }

    public static Date getEpoch() {
        if (epoch == null) {
            epoch = constructEpoch();
        }
        return Date.from(epoch);
    }

    private static Instant constructEpoch(){
        return Instant.EPOCH;
    }
}

