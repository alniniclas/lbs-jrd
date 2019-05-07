package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

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
