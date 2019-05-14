package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes using {@code .class}.
 */
@ThreadSafe
public class SynchronizedStaticStringHolder {
    @GuardedBy("SynchronizedStaticStringHolder.class")
    private static String string = "";

    // Disallow instantiation.
    private SynchronizedStaticStringHolder() {
    }

    public static synchronized void setString(String string) {
        SynchronizedStaticStringHolder.string = string;
    }

    public static synchronized String getString() {
        return string;
    }
}
