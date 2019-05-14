package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes using volatile reference. Thread safe as long as calls to {@code setString()} do not
 * depend on the value returned by {@code getString()}.
 */
@ThreadSafe
public class VolatileStaticStringHolder {
    private static volatile String string = "";

    public static void setString(String string) {
        VolatileStaticStringHolder.string = string;
    }

    public static String getString() {
        return string;
    }
}
