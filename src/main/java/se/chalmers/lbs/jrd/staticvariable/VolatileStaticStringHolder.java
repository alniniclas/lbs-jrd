package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;

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
