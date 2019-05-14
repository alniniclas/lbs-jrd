package se.chalmers.lbs.jrd.fifo;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Blocking. Attempts to block using {@code Thread.sleep()} with no additional synchronization. May
 * result in infinite loop if the compiler optimizes away the null checks in the while loops. Not
 * actually thread safe.
 */
@ThreadSafe
public class SleepBlockingSingleElementFifo<T> implements BlockingFifo<T> {
    private T item;

    @Override
    public void enqueue(T item) throws InterruptedException {
        while (this.item != null) {
            Thread.sleep(10);
        }
        this.item = item;
    }

    @Override
    public T dequeue() throws InterruptedException {
        while (this.item == null) {
            Thread.sleep(10);
        }
        final T item = this.item;
        this.item = null;
        return item;
    }
}
