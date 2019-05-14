package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Synchronizes using a read lock both when reading and writing. Not actually thread safe.
 */
@ThreadSafe
public class ReadLockedCounter implements Counter {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    @GuardedBy("lock")
    private long count;

    public ReadLockedCounter(long count) {
        this.count = count;
    }

    public ReadLockedCounter() {
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
        lock.readLock().lock();
        try {
            return count++;
        } finally {
            lock.readLock().unlock();
        }
    }
}
