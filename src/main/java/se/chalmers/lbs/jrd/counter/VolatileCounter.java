package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes using volatile variable, but performs non-atomic compound operations. Should have no
 * data races, but is still not thread safe due to {@code getAndIncrement} being non-atomic.
 */
@ThreadSafe
public class VolatileCounter implements Counter {
    private volatile long count;

    public VolatileCounter(long count) {
        this.count = count;
    }

    public VolatileCounter() {
        this(0);
    }

    @Override
    public long get() {
        return count;
    }

    @Override
    public long getAndIncrement() {
        return count++;
    }
}
