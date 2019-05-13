package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class HolderClassLazySingletonDate {

    public static Date getEpoch() {
        return new Date(DateHolder.EPOCH.getTime());
    }

    private static class DateHolder {
        private static final Date EPOCH = Date.from(Instant.EPOCH);
    }
}

