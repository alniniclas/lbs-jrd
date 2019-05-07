package se.chalmers.lbs.jrd.counter;

import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface Counter {
    long get();
    long getAndIncrement();
}
