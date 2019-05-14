package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

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
