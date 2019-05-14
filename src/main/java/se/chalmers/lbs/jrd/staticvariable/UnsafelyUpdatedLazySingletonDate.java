package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class UnsafelyUpdatedLazySingletonDate {
    @GuardedBy("UnsafelyUpdatedLazySingletonDate.class")
    private static volatile Date epoch;

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
