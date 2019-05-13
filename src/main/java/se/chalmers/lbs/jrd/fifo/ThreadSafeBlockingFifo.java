package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ThreadSafe
public class ThreadSafeBlockingFifo<T> implements BlockingFifo<T> {
    private final BlockingQueue<T> queue;

    public ThreadSafeBlockingFifo(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void enqueue(T item) throws InterruptedException {
        this.queue.put(item);
    }

    @Override
    public T dequeue() throws InterruptedException {
        return this.queue.take();
    }
}
