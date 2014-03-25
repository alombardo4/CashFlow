package com.mikesandfriends.cashflow;

import android.annotation.SuppressLint;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Holds the information for a Spending Category Report.
 * @author Alec Lombardo
 * @version 1.0
 */
public class SpendingCategoryReport implements Serializable {
	/**
	 * The category of the transaction.
	 * 0 = income
	 * 1 = food
	 * 2 = clothing
	 * 3 = entertainment
	 * 4 = rent
	 */
	/**
	 * Necessary for Serialization.
	 */	
	private static final long serialVersionUID = 1L;
	/**
	 * The spending for each category.
	 */
	private int[] categorySpending = {0, 0, 0, 0, 0};
	
	/**
	 * Builds the report upon creation.
	 * @param transactions All transactions within the requested date range
	 */
	public SpendingCategoryReport(final List<Transaction> transactions) {
		for (Transaction transaction : transactions) {
			for (int i = 1; i < 5; i++) {
				if (transaction.getCategory() == i) {
					categorySpending[i] += -1 * transaction.getAmount();
					
				}
			}
		}
	}
	
	/**
	 * Gets the spending report as a hashmap.
	 * @return The Hashmap with key SpendingCategory value, value amount
	 */
	@SuppressLint("UseSparseArrays")
	public final Map<Integer, Integer> getSpendingReport() {
		final Map<Integer, Integer> retVal = //Not sure why it won't go away
				//we do use ConcurrentHashMap
				new ConcurrentHashMap<Integer, Integer>();
		for (int i = 1; i < categorySpending.length; i++) {
			final int val = categorySpending[i];
			retVal.put(i, val);
		}
		return retVal;
	}
}
