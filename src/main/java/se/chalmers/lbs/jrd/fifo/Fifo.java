package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A simple thread safe fifo queue providing non-blocking enqueue and dequeue operations.
 */
@ThreadSafe
public interface Fifo<T> {
    void enqueue(T item) throws FifoFullException;
    T dequeue() throws FifoEmptyException;
}
