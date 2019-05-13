package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class UnsafeEagerSingletonDate {
    private static final Date EPOCH = Date.from(Instant.EPOCH);

    public static Date getEpoch() {
        return EPOCH;
    }
}

