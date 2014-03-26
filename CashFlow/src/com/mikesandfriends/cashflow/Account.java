package com.mikesandfriends.cashflow;

/**
 * Class to represent an account.
 * @author Alec Lombardo
 * @version 1.0
 */
public class Account {
    /**
     * The name of the account.
     */
    private final transient String name;

    /**
     * Constructor for Account.
     * @param aname Name of the account
     */
    public Account(final String aname) {
        this.name = aname;
    }

    /**
     * Gets the name of the Account.
     * @return name of the account
     */
    public final String getName() {
        return name;
    }

    @Override
    public final int hashCode() {
        return name.hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        boolean retVal = false; //this must be set to false to compile
        if (obj == this) {
            retVal = true;
        } else    if (obj instanceof Account) {
            if (((Account) obj).getName().equals(name)) { //Unnecessarily wordy
                retVal = true;
            }
        } else {
            retVal = false;
        }
        return retVal;
    }
}
