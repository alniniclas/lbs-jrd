package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Non-blocking. Synchronizes using the contained element itself, which is non-final and nullable.
 * When empty, {@code enqueue()} and {@code dequeue()} will throw {@code NullPointerException}. Not
 * actually thread safe.
 */
@ThreadSafe
public class ElementSynchronizedSingleElementFifo<T> implements Fifo<T> {
    @GuardedBy("item")
    private T item;

    @Override
    public void enqueue(T item) throws FifoFullException {
        synchronized (this.item) {
            if (this.item != null) {
                throw new FifoFullException();
            }
            this.item = item;
        }
    }

    @Override
    public T dequeue() throws FifoEmptyException {
        synchronized (this.item) {
            if (this.item == null) {
                throw new FifoEmptyException();
            }
            return this.item;
        }
    }
}
