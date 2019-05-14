package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes {@code getAndIncrement()} using {@code this}, but performs no synchronization for
 * {@code get()}. Not actually thread safe.
 */
@ThreadSafe
public class SynchronizedIncrementCounter implements Counter {
    @GuardedBy("this")
    private long count;

    public SynchronizedIncrementCounter(long count) {
        this.count = count;
    }

    public SynchronizedIncrementCounter() {
        this(0);
    }

    @Override
    public long get() {
        return count;
    }

    @Override
    public synchronized long getAndIncrement() {
        return count++;
    }
}
