package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using a lazily loaded private holder class.
 */
@ThreadSafe
public class HolderClassLazySingletonDate {

    public static Date getEpoch() {
        return new Date(DateHolder.EPOCH.getTime());
    }

    private static class DateHolder {
        private static final Date EPOCH = Date.from(Instant.EPOCH);
    }
}

