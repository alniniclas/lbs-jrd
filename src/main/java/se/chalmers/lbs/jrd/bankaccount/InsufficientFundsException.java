package se.chalmers.lbs.jrd.bankaccount;

/**
 * Thrown when attempting to perform operations on accounts with insufficient funds.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
    }
}
