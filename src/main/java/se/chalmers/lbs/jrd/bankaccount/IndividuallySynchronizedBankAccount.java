package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class IndividuallySynchronizedBankAccount implements BankAccount<IndividuallySynchronizedBankAccount> {

    private static final Object TRANSACTION_LOCK = new Object();
    private final Object lock = new Object();
    @GuardedBy("lock")
    private long balance;

    public IndividuallySynchronizedBankAccount(long balance) {
        this.balance = balance;
    }

    public IndividuallySynchronizedBankAccount() {
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
    public void transfer(long amount, IndividuallySynchronizedBankAccount other) throws InsufficientFundsException {
        synchronized (TRANSACTION_LOCK) {
            withdraw(amount);
            other.deposit(amount);
        }
    }
}
