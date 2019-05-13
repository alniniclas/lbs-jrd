package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SynchronizedBankAccount implements BankAccount<SynchronizedBankAccount> {

    private static final Object TRANSACTION_LOCK = new Object();

    @GuardedBy("TRANSACTION_LOCK")
    private long balance;

    public SynchronizedBankAccount(long balance) {
        this.balance = balance;
    }

    public SynchronizedBankAccount() {
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
    public void transfer(long amount, SynchronizedBankAccount other) throws InsufficientFundsException {
        synchronized (TRANSACTION_LOCK) {
            withdraw(amount);
            other.deposit(amount);
        }
    }
}
