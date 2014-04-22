package com.mikesandfriends.cashflow;

/**
 * Class to represent an account.
 * 
 * @author Alec Lombardo
 * @version 1.0
 */
public class Account {

    /**
     * The number used for the hash.
     */
    private static final int NUMBER = 5;

    /**
     * The name of the account.
     */
    private final transient String name;
    /**
     * Balance of the account.
     */
    private double accountBalance;

    /**
     * Constructor for Account.
     * 
     * @param aname
     *            Name of the account
     */
    public Account(final String aname) {
        this.name = aname;
        accountBalance = 0;
    }

    /**
     * Gets the name of the Account.
     * 
     * @return name of the account
     */
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        int sum = 0;
        if (name.length() > NUMBER) {
            for (int i = 0, n = name.length(); i < n; i++) {
                char c = name.charAt(i);
                int returnValue = (int) c * 2;
                returnValue += 1;
                sum = sum + returnValue;
            }
            return sum;
        } else {
            for (int i = 0, n = name.length(); i < n; i++) {
                char c = name.charAt(i);
                int returnValue = (int) c;
                sum = sum + returnValue;
            }
            return sum;
        }
    }

    @Override
    public final boolean equals(final Object obj) {
        boolean retVal = false; // this must be set to false to compile
        if (obj == this) {
            retVal = true;
        } else if (obj instanceof Account) {
            if (((Account) obj).getName().equals(name)) { // Unnecessarily wordy
                retVal = true;
            }
        } else {
            retVal = false;
        }
        return retVal;
    }

    /**
     * Set the balance to a certain amount.
     * 
     * @param newBalance
     *            the new balance to set
     */
    public final void setBalance(double newBalance) {
        accountBalance = newBalance;
    }

    /**
     * Get the balance.
     */
    public final double getBalance() {
        return accountBalance;
    }

    /**
     * Checks if the balance is still valid after trying a transaction.
     * 
     * @param amount
     *            amount to subtract from balance
     * @return boolean
     */

    public final boolean validAfterWithdrawal(final double amount) {
        double newBalance = accountBalance - amount;
        return newBalance >= 0;
    }
}
