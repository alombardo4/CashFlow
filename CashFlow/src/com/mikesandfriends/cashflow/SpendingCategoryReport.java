/**
 * 
 */
package com.mikesandfriends.cashflow;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holds the information for a Spending Category Report
 * @author Alec Lombardo
 * @version 1.0
 */
public class SpendingCategoryReport {
	public int[] categorySpending;
	
	/**
	 * Builds the report upon creation
	 * @param transactions All transactions within the requested date range
	 */
	public SpendingCategoryReport(ArrayList<Transaction> transactions) {
		categorySpending = new int[SpendingCategory.values().length - 1];
		for (Transaction transaction : transactions) {
			for (int i = 0; i < SpendingCategory.values().length - 1; i++) {
				if (transaction.getCategory().equals(
						SpendingCategory.values()[i])) {
					categorySpending[i] += -1 * transaction.getAmount();
					
				}
			}
		}
	}
	
	/**
	 * Gets the spending report as a hashmap
	 * @return The Hashmap with key SpendingCategory value, value amount
	 */
	@SuppressLint("UseSparseArrays")
	public HashMap<Integer, Integer> getSpendingReport() {
		HashMap<Integer, Integer> retVal = new HashMap<Integer, Integer>();
		for (int i = 0; i < categorySpending.length; i++) {
			retVal.put(i, categorySpending[i]);
		}
		return retVal;
	}
}
