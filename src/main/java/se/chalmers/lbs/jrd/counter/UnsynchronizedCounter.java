package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Performs no synchronization. Not actually thread safe.
 */
@ThreadSafe
public class UnsynchronizedCounter implements Counter {
    private long count;

    public UnsynchronizedCounter(long count) {
        this.count = count;
    }

    public UnsynchronizedCounter() {
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
