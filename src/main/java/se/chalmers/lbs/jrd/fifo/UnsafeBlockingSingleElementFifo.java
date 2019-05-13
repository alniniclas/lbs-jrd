package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

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
        }
    }

    @Override
    public T dequeue() throws InterruptedException {
        synchronized (lock) {
            if (this.item == null) {
                lock.wait();
            }
            return this.item;
        }
    }
}
