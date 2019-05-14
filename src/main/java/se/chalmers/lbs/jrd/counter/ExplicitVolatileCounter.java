package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes using volatile variable, with no compound operations. Should have no data races, but
 * is still not thread safe due to {@code getAndIncrement} being non-atomic.
 */
@ThreadSafe
public class ExplicitVolatileCounter implements Counter {
    private volatile long count;

    public ExplicitVolatileCounter(long count) {
        this.count = count;
    }

    public ExplicitVolatileCounter() {
        this(0);
    }

    @Override
    public long get() {
        return count;
    }

    @Override
    public long getAndIncrement() {
        long current = count;
        count = current + 1;
        return current;
    }
}
