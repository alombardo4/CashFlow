package com.mikesandfriends.cashflow;

import java.io.Serializable;
/**
 * Represents a User for login.
 * @author Alec Lombardo
 * @version 1.0
 */
public class User implements Serializable { //a longer name isn't as descriptive
    /**
     * Necessary for Serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The user's username.
     */
    private String username;
    /**
     * The user's password.
     */
    private String password;

    /**
     * Main constructor.
     * @param pusername User's username
     * @param ppassword User's plain text password
     */
    public User(final String pusername, final String ppassword) {
        this.username = pusername;
        this.password = ppassword;
    }

    /**
     * No-args constructor.
     */
    public User() {
        this("", "");
    }

    /**
     * Gets the username.
     * @return the username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param pusername the username to set
     */
    public final void setUsername(final String pusername) {
        this.username = pusername;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param ppassword the password to set
     */
    public final void setPassword(final String ppassword) {
        this.password = ppassword;
    }

    @Override
    public final boolean equals(final Object other) {
        boolean retVal;
        if (other == null) {
            retVal = false;
            //Don't want to make this unneccesessarily wordy.
        } else if (this.username.equals(((User) other).getUsername())) {
            //Don't want to make this unneccesessarily wordy.
            retVal = this.password.equals(((User) other).getPassword());
        } else {
            retVal = false;
        }
        return retVal;
    }

    @Override
    public final int hashCode() {
        return username.hashCode();
    }

    @Override
    public final String toString() {
        return username;
    }

}
