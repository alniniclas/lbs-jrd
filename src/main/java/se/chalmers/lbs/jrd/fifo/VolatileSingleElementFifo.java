package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class VolatileSingleElementFifo<T> implements Fifo<T> {
    private volatile T item;

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
