package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Performs no synchronization. Contains data race but may still be thread safe if seeing stale
 * values is considered acceptable.
 */
@ThreadSafe
public class UnsynchronizedStaticStringHolder {
    private static String string = "";

    public static void setString(String string) {
        UnsynchronizedStaticStringHolder.string = string;
    }

    public static String getString() {
        return string;
    }
}
