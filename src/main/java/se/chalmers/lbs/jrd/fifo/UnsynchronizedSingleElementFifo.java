package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Non-blocking. Performs no synchronization. Not actually thread safe.
 */
@ThreadSafe
public class UnsynchronizedSingleElementFifo<T> implements Fifo<T> {
    private T item;

    @Override
    public void enqueue(T item) throws FifoFullException {
        if (this.item != null) {
            throw new FifoFullException();
        }
        this.item = item;
    }

    @Override
    public T dequeue() throws FifoEmptyException {
        if (this.item == null) {
            throw new FifoEmptyException();
        }
        return this.item;
    }
}
