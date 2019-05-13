package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayDeque;
import java.util.Queue;

@ThreadSafe
public class SynchronizedBlockingFifo<T> implements BlockingFifo<T> {

    @GuardedBy("queue")
    private final Queue<T> queue = new ArrayDeque<>();

    @Override
    public void enqueue(T item) {
        synchronized (queue) {
            this.queue.add(item);
            this.queue.notifyAll();
        }
    }

    @Override
    public T dequeue() throws InterruptedException {
        T item;
        synchronized (queue) {
            for (item = this.queue.poll(); item == null; item = this.queue.poll()) {
                queue.wait();
            }
        }
        return item;
    }
}
