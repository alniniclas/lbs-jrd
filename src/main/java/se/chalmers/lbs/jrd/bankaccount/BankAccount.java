package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A simple thread safe bank account providing deposit, withdraw and transfer operations.
 */
@ThreadSafe
public interface BankAccount<T extends BankAccount<T>> {
    void deposit(long amount);

    void withdraw(long amount) throws InsufficientFundsException;

    void transfer(long amount, T other) throws InsufficientFundsException;
}
