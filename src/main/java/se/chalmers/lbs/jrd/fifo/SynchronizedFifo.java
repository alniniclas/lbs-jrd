package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Non-blocking. Synchronizes on private queue object.
 */
@ThreadSafe
public class SynchronizedFifo<T> implements Fifo<T> {

    @GuardedBy("queue")
    private final Queue<T> queue = new ArrayDeque<>();

    @Override
    public void enqueue(T item) {
        synchronized (queue) {
            this.queue.add(item);
        }
    }

    @Override
    public T dequeue() throws FifoEmptyException {
        final T item;
        synchronized (queue) {
            item = this.queue.poll();
        }
        if (item == null) {
            throw new FifoEmptyException();
        }
        return item;
    }
}
