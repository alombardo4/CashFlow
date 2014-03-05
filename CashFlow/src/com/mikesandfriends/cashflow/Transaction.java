package com.mikesandfriends.cashflow;

import java.util.GregorianCalendar;

/**
 * Represents a Transaction
 * @author Alec Lombardo
 * @version 1.0
 */
public class Transaction {
	private String name;
	private int amount;
	private SpendingCategory category;
	private GregorianCalendar date;
	
	/**
	 * Transaction constructor
	 * @param name Name of the transaction
	 * @param amount Amount of the transaction (positive or negative)
	 * @param category Spending category of the transaction
	 * @param date Date transaction entered
	 */
	public Transaction(String name, int amount, SpendingCategory category,
			GregorianCalendar date) {
		super();
		this.name = name;
		this.amount = amount;
		this.category = category;
		this.date = date;
	}

	/**
	 * Gets the spending category
	 * @return the category
	 */
	public SpendingCategory getCategory() {
		return category;
	}

	/**
	 * Sets the spending category
	 * @param category the category to set
	 */
	public void setCategory(SpendingCategory category) {
		this.category = category;
	}

	/**
	 * Gets the date
	 * @return the date
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date the date to set
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
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
