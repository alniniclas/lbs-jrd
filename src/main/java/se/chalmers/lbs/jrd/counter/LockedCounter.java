package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class LockedCounter implements Counter {
    private final Lock lock = new ReentrantLock();
    @GuardedBy("lock")
    private long count;

    public LockedCounter(long count) {
        this.count = count;
    }

    public LockedCounter() {
        this(0);
    }

    @Override
    public long get() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
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
