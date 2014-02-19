package com.mikesandfriends.cashflow;

/**
 * Class to represent an account
 * @author Alec Lombardo
 * @version 1.0
 */
public class Account {
	private String name;
	
	/**
	 * Constructor for Account
	 * @param name Name of the account
	 */
	public Account(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the Account
	 * @return name of the account
	 */
	public String getName() {
		return name;
	}
	
}
