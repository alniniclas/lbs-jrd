package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Fifo<T> {
    void enqueue(T item) throws FifoFullException;
    T dequeue() throws FifoEmptyException;
}
