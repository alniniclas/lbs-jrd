package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface BankAccount<T extends BankAccount<T>> {
    void deposit(long amount);

    void withdraw(long amount) throws InsufficientFundsException;

    void transfer(long amount, T other) throws InsufficientFundsException;
}
