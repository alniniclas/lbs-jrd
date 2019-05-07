package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SynchronizedGetCounter implements Counter {
    @GuardedBy("this")
    private long count;

    public SynchronizedGetCounter(long count) {
        this.count = count;
    }

    public SynchronizedGetCounter() {
        this(0);
    }

    @Override
    public synchronized long get() {
        return count;
    }

    @Override
    public long getAndIncrement() {
        return count++;
    }
}
