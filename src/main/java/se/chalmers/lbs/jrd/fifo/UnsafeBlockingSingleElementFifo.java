package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Blocking. Synchronizes using private lock object. Uses if-statement rather than while-loop for
 * checking the blocking conditions, causing incorrect behavior if the condition is not met upon
 * wake-up (e.g. in the case of multiple calls to {@code enqueue} or {@code dequeue} being woken up,
 * or in the case of a spurious wake-up). Not actually thread safe.
 */
@ThreadSafe
public class UnsafeBlockingSingleElementFifo<T> implements BlockingFifo<T> {
    private final Object lock = new Object();
    @GuardedBy("lock")
    private T item;

    @Override
    public void enqueue(T item) throws InterruptedException {
        synchronized (lock) {
            if (this.item != null) {
                lock.wait();
            }
            this.item = item;
            this.lock.notifyAll();
        }
    }

    @Override
    public T dequeue() throws InterruptedException {
        synchronized (lock) {
            if (this.item == null) {
                lock.wait();
            }
            final T item = this.item;
            this.item = null;
            this.lock.notifyAll();
            return item;
        }
    }
}
