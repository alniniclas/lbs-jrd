package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class OrderedSynchronizationBankAccount implements BankAccount<OrderedSynchronizationBankAccount> {

    private static final AtomicLong nextId = new AtomicLong(0);
    private final long id;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private long balance;

    public OrderedSynchronizationBankAccount(long balance) {
        this.id = nextId.getAndIncrement();
        this.balance = balance;
    }

    public OrderedSynchronizationBankAccount() {
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
    public void transfer(long amount, OrderedSynchronizationBankAccount other) throws InsufficientFundsException {
        Object lock1;
        Object lock2;

        if (this.id < other.id){
            lock1 = this.lock;
            lock2 = other.lock;
        }
        else {
            lock1 = other.lock;
            lock2 = this.lock;
        }
        synchronized (lock1) {
            synchronized (lock2) {
                if (balance < amount) {
                    throw new InsufficientFundsException();
                }
                balance -= amount;
                other.balance += amount;
            }
        }
    }
}
