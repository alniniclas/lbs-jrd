package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Non-blocking. Performs no synchronization. Not actually thread safe.
 */
@ThreadSafe
public class UnsynchronizedFifo<T> implements Fifo<T> {
    private final Queue<T> queue = new ArrayDeque<>();

    @Override
    public void enqueue(T item) {
        this.queue.add(item);
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
