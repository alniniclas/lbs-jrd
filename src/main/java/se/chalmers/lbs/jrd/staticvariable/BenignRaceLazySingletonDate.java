package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

@ThreadSafe
public class BenignRaceLazySingletonDate {
    private static Instant epoch;

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

