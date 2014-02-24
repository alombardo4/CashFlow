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
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Account)) {
			return false;
		}
		if (((Account)o).getName().equals(name)) {
			return true;
		}
		return false;
	}
}
