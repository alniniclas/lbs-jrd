package se.chalmers.lbs.jrd.counter;

public interface Counter {
    long get();
    long getAndIncrement();
}
