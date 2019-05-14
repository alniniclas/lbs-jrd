package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.Semaphore;

/**
 * Synchronizes using a semaphore with a single permit. Thread safe, but possibly dubious coding
 * practice.
 */
@ThreadSafe
public class SinglePermitSemaphoreCounter implements Counter {
    private final Semaphore semaphore = new Semaphore(1);
    @GuardedBy("semaphore")
    private long count;

    public SinglePermitSemaphoreCounter(long count) {
        this.count = count;
    }

    public SinglePermitSemaphoreCounter() {
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
