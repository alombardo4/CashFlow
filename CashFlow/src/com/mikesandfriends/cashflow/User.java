package com.mikesandfriends.cashflow;

import java.io.Serializable;

import android.graphics.Color;

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
     * The user's email address.
     */
    private String email;
    
    /**
     * The user's security question
     */
    private String securityQuestion;
    
    /**
     * The user's security answer;
     */
    private String securityAnswer;
    
    /**
     * The user's background color
     */
    private int background;
    
    /**
     * Main constructor.
     * @param pusername User's username
     * @param ppassword User's plain text password
     */
    public User(final String pusername, final String ppassword, final String
    		pemail, final String psecurityQuestion,
    		final String pSecurityAnswer, final int pbackground) {
        this.username = pusername;
        this.password = ppassword;
        this.email = pemail;
        this.securityQuestion = psecurityQuestion;
        this.securityAnswer = pSecurityAnswer;
        this.background = pbackground;
    }

    public User(final String pusername, final String ppassword, final String
    		pemail, final String psecurityQuestion,
    		final String pSecurityAnswer) {
    	this(pusername, ppassword, pemail, psecurityQuestion, pSecurityAnswer,
    			Color.WHITE);
    }
    
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

    /**
     * Gets user's email.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets user's email.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets user's security question.
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * Sets the user's security question.
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * Get's the user's security answer
	 * @return the securityAnswer
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * Set's the user's security answer
	 * @param securityAnswer the securityAnswer to set
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	/**
	 * Gets the user's background color
	 * @return the background
	 */
	public int getBackground() {
		return background;
	}

	/**
	 * Sets the user's background color
	 * @param background the background to set
	 */
	public void setBackground(int background) {
		this.background = background;
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
