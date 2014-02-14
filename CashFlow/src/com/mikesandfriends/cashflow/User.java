package com.mikesandfriends.cashflow;

/**
 * Represents a User for login
 * @author Alec Lombardo
 * @version 1.0
 */
public class User {
	private String username;
	private String password;
	
	/**
	 * Main constructor
	 * @param username User's username
	 * @param password User's plain text password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * No-args constructor
	 */
	public User() {
		this("", "");
	}

	/**
	 * Gets the username
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean equals(Object other) {
		if(this.username.equals(((User) other).getUsername())) {
			if(this.password.equals(((User) other).getPassword())) {
				return true;	
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	public String toString(){
		return username;
	}
	
}
