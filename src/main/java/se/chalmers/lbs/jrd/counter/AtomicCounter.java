package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class AtomicCounter implements Counter {
    private final AtomicLong count;

    public AtomicCounter(long count) {
        this.count = new AtomicLong(count);
    }

    public AtomicCounter() {
        this(0);
    }

    @Override
    public long get() {
        return count.get();
    }

    @Override
    public long getAndIncrement() {
        return count.getAndIncrement();
    }
}
