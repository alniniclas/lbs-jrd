package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

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
