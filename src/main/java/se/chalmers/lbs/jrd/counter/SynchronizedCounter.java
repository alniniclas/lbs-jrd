package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes using {@code this}.
 */
@ThreadSafe
public class SynchronizedCounter implements Counter {
    @GuardedBy("this")
    private long count;

    public SynchronizedCounter(long count) {
        this.count = count;
    }

    public SynchronizedCounter() {
        this(0);
    }

    @Override
    public synchronized long get() {
        return count;
    }

    @Override
    public synchronized long getAndIncrement() {
        return count++;
    }
}
