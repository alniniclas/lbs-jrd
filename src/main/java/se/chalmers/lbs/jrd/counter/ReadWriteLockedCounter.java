package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Synchronizes using a read-write lock.
 */
@ThreadSafe
public class ReadWriteLockedCounter implements Counter {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    @GuardedBy("lock")
    private long count;

    public ReadWriteLockedCounter(long count) {
        this.count = count;
    }

    public ReadWriteLockedCounter() {
        this(0);
    }

    @Override
    public long get() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public long getAndIncrement() {
        lock.writeLock().lock();
        try {
            return count++;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
