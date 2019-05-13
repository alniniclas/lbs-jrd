package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class UnsynchronizedLazySingletonDate {
    private static Date epoch;

    public static Date getEpoch() {
        if (epoch == null) {
            epoch = Date.from(Instant.EPOCH);
        }
        return new Date(epoch.getTime());
    }
}

