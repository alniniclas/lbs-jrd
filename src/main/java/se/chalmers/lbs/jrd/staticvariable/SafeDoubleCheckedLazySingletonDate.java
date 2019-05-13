package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class SafeDoubleCheckedLazySingletonDate {
    @GuardedBy("SynchronizedLazySingletonDate.class")
    private static volatile Date epoch;

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

