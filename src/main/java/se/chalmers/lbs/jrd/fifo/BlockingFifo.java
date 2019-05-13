package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface BlockingFifo<T> {
    void enqueue(T item) throws InterruptedException;
    T dequeue() throws InterruptedException;
}
