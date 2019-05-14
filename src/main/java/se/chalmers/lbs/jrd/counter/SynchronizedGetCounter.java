package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes {@code get()} using {@code this}, but performs no synchronization for
 * {@code getAndIncrement()}. Not actually thread safe.
 */
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
