package com.mikesandfriends.cashflow;

/**
 * Represents a Transaction
 * @author Alec Lombardo
 * @version 1.0
 */
public class Transaction {
	private String name;
	private int amount;
	
	/**
	 * Transaction constructor
	 * @param name Name of the transaction
	 * @param amount Amount of the transaction (positive or negative)
	 */
	public Transaction(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	/**
	 * Gets name of the transaction
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the transaction
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the amount of the transaction
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the transaction
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
