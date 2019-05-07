package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.Semaphore;

@ThreadSafe
public class MultiPermitSemaphoreCounter implements Counter {
    private final Semaphore semaphore = new Semaphore(10);
    @GuardedBy("semaphore")
    private long count;

    public MultiPermitSemaphoreCounter(long count) {
        this.count = count;
    }

    public MultiPermitSemaphoreCounter() {
        this(0);
    }

    @Override
    public long get() {
        semaphore.acquireUninterruptibly();
        try {
            return count;
        } finally {
            semaphore.release();
        }
    }

    @Override
    public long getAndIncrement() {
        semaphore.acquireUninterruptibly();
        try {
            return count++;
        } finally {
            semaphore.release();
        }
    }
}
