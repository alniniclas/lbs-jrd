package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Synchronizes using a static transaction lock but releases the lock between separate operations
 * during transactions. Should have no data races, but is still not thread safe due to transfer
 * being non-atomic.
 */
@ThreadSafe
public class NonAtomicTransferAccount implements BankAccount<NonAtomicTransferAccount> {

    private static final Object TRANSACTION_LOCK = new Object();

    @GuardedBy("TRANSACTION_LOCK")
    private long balance;

    public NonAtomicTransferAccount(long balance) {
        this.balance = balance;
    }

    public NonAtomicTransferAccount() {
        this(0);
    }

    @Override
    public void deposit(long amount) {
        synchronized (TRANSACTION_LOCK) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
        synchronized (TRANSACTION_LOCK) {
            if (balance < amount) {
                throw new InsufficientFundsException();
            }
            balance -= amount;
        }
    }

    @Override
    public void transfer(long amount, NonAtomicTransferAccount other) throws InsufficientFundsException {
        long balance;
        synchronized (TRANSACTION_LOCK) {
            balance = this.balance;
        }
        if (balance < amount) {
            throw new InsufficientFundsException();
        }
        synchronized (TRANSACTION_LOCK) {
            other.balance += amount;
        }
        synchronized (TRANSACTION_LOCK) {
            this.balance -= amount;
        }

    }
}
