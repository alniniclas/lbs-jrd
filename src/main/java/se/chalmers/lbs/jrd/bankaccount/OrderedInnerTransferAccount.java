package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Synchronizes using individual private lock object. To avoid deadlocks, uses the unique ID of
 * accounts to always acquire locks in the same order. Avoids using local variables for
 * synchronization. Thread safe, but may be confusing for tools due to performing work in private
 * method with locks acquired externally.
 */
@ThreadSafe
public class OrderedInnerTransferAccount implements BankAccount<OrderedInnerTransferAccount> {

    private static final AtomicLong nextId = new AtomicLong(0);
    private final long id;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private long balance;

    public OrderedInnerTransferAccount(long balance) {
        this.id = nextId.getAndIncrement();
        this.balance = balance;
    }

    public OrderedInnerTransferAccount() {
        this(0);
    }

    @Override
    public void deposit(long amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
        synchronized (lock) {
            if (balance < amount) {
                throw new InsufficientFundsException();
            }
            balance -= amount;
        }
    }


    @Override
    public void transfer(long amount, OrderedInnerTransferAccount other) throws InsufficientFundsException {
        if (this.id < other.id) {
            synchronized (this.lock) {
                synchronized (other.lock) {
                    innerTransfer(amount, other);
                }
            }
        } else {
            synchronized (other.lock) {
                synchronized (this.lock) {
                    innerTransfer(amount, other);
                }
            }
        }
    }

    private void innerTransfer(long amount, OrderedInnerTransferAccount other) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
        other.balance += amount;
    }
}

