package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SynchronizedSingleElementFifo<T> implements Fifo<T> {
    private final Object lock = new Object();
    @GuardedBy("lock")
    private T item;

    @Override
    public void enqueue(T item) throws FifoFullException {
        synchronized (lock) {
            if (this.item != null) {
                throw new FifoFullException();
            }
            this.item = item;
        }
    }

    @Override
    public T dequeue() throws FifoEmptyException {
        synchronized (lock) {
            if (this.item == null) {
                throw new FifoEmptyException();
            }
            return this.item;
        }
    }
}
