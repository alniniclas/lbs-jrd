package se.chalmers.lbs.jrd.counter;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A simple thread safe counter providing get and get-and-increment operations.
 */
@ThreadSafe
public interface Counter {
    long get();
    long getAndIncrement();
}
