package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Counter {
    long get();
    long getAndIncrement();
}
