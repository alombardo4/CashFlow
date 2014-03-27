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
     * 0 = income
     * 1 = food
     * 2 = clothing
     * 3 = entertainment
     * 4 = rent
     */
    private int category;
    /**
     * The date of the transaction.
     */
    private GregorianCalendar date;

    /**
     * Transaction constructor.
     * @param pname Name of the transaction
     * @param pamount Amount of the transaction (positive or negative)
     * @param pcategory Spending category of the transaction
     * @param pdate Date transaction entered
     */
    public Transaction(final String pname, final int pamount,
            final int pcategory, final GregorianCalendar pdate) {
        super();
        this.name = pname;
        this.amount = pamount;
        this.category = pcategory;
        this.date = pdate;
    }

    /**
     * Gets the spending category.
     * @return the category
     */
    public final int getCategory() {
        return category;
    }

    /**
     * Sets the spending category.
     * @param pcategory the category to set
     */
    public final void setCategory(final int pcategory) {
        this.category = pcategory;
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
     * @param pdate the date to set
     */
    public final void setDate(final GregorianCalendar pdate) {
        this.date = pdate;
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
     * @param pname the name to set
     */
    public final void setName(final String pname) {
        this.name = pname;
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
     * @param pamount the amount to set
     */
    public final void setAmount(final int pamount) {
        this.amount = pamount;
    }

    @Override
    public final String toString() {
        return name + " " + amount + " " + category + " "
                + date.getTimeInMillis();
    }
}
