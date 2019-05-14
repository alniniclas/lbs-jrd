package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A simple thread safe fifo queue providing blocking enqueue and dequeue operations.
 */
@ThreadSafe
public interface BlockingFifo<T> {
    void enqueue(T item) throws InterruptedException;
    T dequeue() throws InterruptedException;
}
