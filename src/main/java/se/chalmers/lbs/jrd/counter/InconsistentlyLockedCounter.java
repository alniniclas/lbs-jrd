package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class InconsistentlyLockedCounter implements Counter {
    private final Lock lock = new ReentrantLock();
    @GuardedBy("lock")
    private long count;

    public InconsistentlyLockedCounter(long count) {
        this.count = count;
    }

    public InconsistentlyLockedCounter() {
        this(0);
    }

    @Override
    public long get() {
        synchronized (lock) {
            return count;
        }
    }

    @Override
    public long getAndIncrement() {
        lock.lock();
        try {
            return count++;
        } finally {
            lock.unlock();
        }
    }
}
