package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ThreadSafe
public class ThreadSafeFifo<T> implements Fifo<T> {
    private final BlockingQueue<T> queue;

    public ThreadSafeFifo(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void enqueue(T item) throws FifoFullException {
        boolean success = this.queue.offer(item);
        if (!success) {
            throw new FifoFullException();
        }
    }

    @Override
    public T dequeue() throws FifoEmptyException {
        final T item = this.queue.poll();
        if (item == null) {
            throw new FifoEmptyException();
        }
        return item;
    }
}
