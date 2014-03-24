package com.mikesandfriends.cashflow;

import java.util.GregorianCalendar;

/**
 * Represents a Transaction.
 * @author Alec Lombardo
 * @version 1.0
 */
public class Transaction {
	/**
	 * The name of the transaction.
	 */
	private String name;
	/**
	 * The amount of the transaction.
	 */
	private int amount;
	/**
	 * The category of the transaction.
	 */
	private SpendingCategory category;
	/**
	 * The date of the transaction.
	 */
	private GregorianCalendar date;
	
	/**
	 * Transaction constructor.
	 * @param name Name of the transaction
	 * @param amount Amount of the transaction (positive or negative)
	 * @param category Spending category of the transaction
	 * @param date Date transaction entered
	 */
	public Transaction(final String name, final int amount,
			final SpendingCategory category, final GregorianCalendar date) {
		super();
		this.name = name;
		this.amount = amount;
		this.category = category;
		this.date = date;
	}

	/**
	 * Gets the spending category.
	 * @return the category
	 */
	public final SpendingCategory getCategory() {
		return category;
	}

	/**
	 * Sets the spending category.
	 * @param category the category to set
	 */
	public final void setCategory(final SpendingCategory category) {
		this.category = category;
	}

	/**
	 * Gets the date.
	 * @return the date
	 */
	public final GregorianCalendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * @param date the date to set
	 */
	public final void setDate(final GregorianCalendar date) {
		this.date = date;
	}

	/**
	 * Gets name of the transaction.
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name of the transaction.
	 * @param name the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the amount of the transaction.
	 * @return the amount
	 */
	public final int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the transaction.
	 * @param amount the amount to set
	 */
	public final void setAmount(final int amount) {
		this.amount = amount;
	}
	
	@Override
	public final String toString() {
		return name + " " + amount + " " + category + " "
				+ date.getTimeInMillis();
	}
}
