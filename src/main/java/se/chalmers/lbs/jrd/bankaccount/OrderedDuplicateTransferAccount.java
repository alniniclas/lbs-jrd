package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Synchronizes using individual private lock object. To avoid deadlocks, uses the unique ID of
 * accounts to always acquire locks in the same order. Avoids using local variables for
 * synchronization and performing work in private methods with locks acquired externally. Thread
 * safe, but requires code duplication.
 */
@ThreadSafe
public class OrderedDuplicateTransferAccount implements BankAccount<OrderedDuplicateTransferAccount> {

    private static final AtomicLong nextId = new AtomicLong(0);
    private final long id;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private long balance;

    public OrderedDuplicateTransferAccount(long balance) {
        this.id = nextId.getAndIncrement();
        this.balance = balance;
    }

    public OrderedDuplicateTransferAccount() {
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
    public void transfer(long amount, OrderedDuplicateTransferAccount other) throws InsufficientFundsException {
        if (this.id < other.id) {
            synchronized (this.lock) {
                synchronized (other.lock) {
                    if (balance < amount) {
                        throw new InsufficientFundsException();
                    }
                    balance -= amount;
                    other.balance += amount;
                }
            }
        } else {
            synchronized (other.lock) {
                synchronized (this.lock) {
                    if (balance < amount) {
                        throw new InsufficientFundsException();
                    }
                    balance -= amount;
                    other.balance += amount;
                }
            }
        }
    }
}

