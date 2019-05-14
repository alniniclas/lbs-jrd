package se.chalmers.lbs.jrd.staticvariable;

import com.facebook.infer.annotation.Functional;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Date;

/**
 * Synchronizes using benign race. Has a data race but should still be thread safe. Annotated to
 * help Infer realise that {@code constructEpoch()} always returns the same result.
 */
@ThreadSafe
public class AnnotatedBenignRaceLazySingletonDate {
    private static Instant epoch;

    // Disallow instantiation.
    private AnnotatedBenignRaceLazySingletonDate() {
    }

    public static Date getEpoch() {
        if (epoch == null) {
            epoch = constructEpoch();
        }
        return Date.from(epoch);
    }

    @Functional
    private static Instant constructEpoch() {
        return Instant.EPOCH;
    }
}

