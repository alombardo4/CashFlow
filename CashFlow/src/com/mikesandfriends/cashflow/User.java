package com.mikesandfriends.cashflow;

import java.io.Serializable;

import android.graphics.Color;

/**
 * Represents a User for login.
 *
 * @author Alec Lombardo
 * @version 1.0
 */
public class User implements Serializable { // a longer name isn't as
                                            // descriptive
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
     * The user's email address.
     */
    private String email;

    /**.
     * The user's security question
     */
    private String securityQuestion;

    /**.
     * The user's security answer;
     */
    private String securityAnswer;

    /**.
     * The user's background color
     */
    private int background;

    /**
     * Main constructor.
     *
     * @param pusername
     *            User's username
     * @param ppassword
     *            User's plain text password
     * @param pemail
     *            User's plain text email
     *
     * @param psecurityQuestion
     *            User's plain text securityquesiton
     * @param pSecurityAnswer
     *            User's plain text security answer
     *
     * @param pbackground
     *            User's plain text pbackground
     *
     */
    public User(final String pusername, final String ppassword,
            final String pemail, final String psecurityQuestion,
            final String pSecurityAnswer, final int pbackground) {
        this.username = pusername;
        this.password = ppassword;
        this.email = pemail;
        this.securityQuestion = psecurityQuestion;
        this.securityAnswer = pSecurityAnswer;
        this.background = pbackground;
    }

    /**
     * Main constructor.
     *
     * @param pusername
     *            User's username
     * @param ppassword
     *            User's plain text password
     * @param pemail
     *            User's plain text email
     *
     * @param psecurityQuestion
     *            User's plain text securityquesiton
     * @param pSecurityAnswer
     *            User's plain text security answer
     *
     */
    public User(final String pusername, final String ppassword,
            final String pemail, final String psecurityQuestion,
            final String pSecurityAnswer) {
        this(pusername, ppassword, pemail, psecurityQuestion, pSecurityAnswer,
                Color.WHITE);
    }

    /**
     * Main constructor.
     * 
     * @param pusername
     *            User's username
     * @param ppassword
     *            User's plain text password
     * 
     */
    public User(final String pusername, final String ppassword) {
        this(pusername, ppassword, "", "", "", Color.WHITE);
    }

    /**
     * No-args constructor.
     */
    public User() {
        this("", "", "", "", "", Color.WHITE);
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param pusername
     *            the username to set
     */
    public final void setUsername(final String pusername) {
        this.username = pusername;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param ppassword
     *            the password to set
     */
    public final void setPassword(final String ppassword) {
        this.password = ppassword;
    }

    /**
     * Gets user's email.
     *
     * @return the email
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Sets user's email.
     *
     * @param pemail
     *            the email to set
     */
    public final void setEmail(final String pemail) {
        this.email = pemail;
    }

    /**
     * Gets user's security question.
     *
     * @return the securityQuestion
     */
    public final String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * Sets the user's security question.
     *
     * @param psecurityQuestion
     *            the securityQuestion to set
     */
    public final void setSecurityQuestion(final String psecurityQuestion) {
        this.securityQuestion = psecurityQuestion;
    }

    /**.
     * Get's the user's security answer
     *
     * @return the securityAnswer
     */
    public final String getSecurityAnswer() {
        return securityAnswer;
    }

    /**.
     * Set's the user's security answer
     *
     * @param psecurityAnswer
     *            the securityAnswer to set
     */
    public final void setSecurityAnswer(final String psecurityAnswer) {
        this.securityAnswer = psecurityAnswer;
    }

    /**.
     * Gets the user's background color
     *
     * @return the background
     */
    public final int getBackground() {
        return background;
    }

    /**.
     * Sets the user's background color
     *
     * @param pbackground
     *            the background to set
     */
    public final void setBackground(final int pbackground) {
        this.background = pbackground;
    }

    @Override
    public final boolean equals(final Object other) {
        boolean retVal;
        if (other == null) {
            retVal = false;
            // Don't want to make this unneccesessarily wordy.
        } else if (this.username.equals(((User) other).getUsername())) {
            // Don't want to make this unneccesessarily wordy.
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
