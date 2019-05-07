package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

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
