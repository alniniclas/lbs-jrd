package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class UnsafeDoubleCheckedLazySingletonDate {
    @GuardedBy("SynchronizedLazySingletonDate.class")
    private static Date epoch;

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

