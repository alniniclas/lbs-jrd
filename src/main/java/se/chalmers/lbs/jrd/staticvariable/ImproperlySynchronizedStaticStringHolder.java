package se.chalmers.lbs.jrd.staticvariable;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ImproperlySynchronizedStaticStringHolder {
    @GuardedBy("this")
    private static String string = "";

    public synchronized void setString(String string) {
        ImproperlySynchronizedStaticStringHolder.string = string;
    }

    public synchronized String getString() {
        return string;
    }
}