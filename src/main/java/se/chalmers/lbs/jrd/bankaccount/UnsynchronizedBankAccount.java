package se.chalmers.lbs.jrd.bankaccount;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class UnsynchronizedBankAccount implements BankAccount<UnsynchronizedBankAccount> {

    private long balance;

    public UnsynchronizedBankAccount(long balance) {
        this.balance = balance;
    }

    public UnsynchronizedBankAccount() {
        this(0);
    }

    @Override
    public void deposit(long amount) {
        balance += amount;
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
    }

    @Override
    public void transfer(long amount, UnsynchronizedBankAccount other) throws InsufficientFundsException {
        withdraw(amount);
        other.deposit(amount);
    }
}
