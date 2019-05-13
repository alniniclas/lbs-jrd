package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.ThreadSafe;

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
