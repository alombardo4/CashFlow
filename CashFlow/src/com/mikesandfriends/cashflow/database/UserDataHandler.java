package com.mikesandfriends.cashflow.database;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.SpendingCategoryReport;
import com.mikesandfriends.cashflow.Transaction;
import com.mikesandfriends.cashflow.User;

import android.content.Context;

/**
 * Handles getting all data for a user from the database.
 *
 * @author Michael Avery
 * @author Alec Lombardo
 * @version 1.0
 *
 */
public class UserDataHandler {
    /**
     * Holds the CashFlowDBAdapter that the UserDataHandler is abstracting.
     */
    private final CashFlowDBAdapter adapt;

    /**
     * Login Check constructor.
     *
     * @param context
     *            The context provided by the activity calling.
     */

    public UserDataHandler(final Context context) {
        adapt = new CashFlowDBAdapter(context);
        adapt.open();
        final User admin = new User("admin", "pass1234");
        if (!checkLogin(admin)) {
            add(admin);
        }
    }

    /**
     * Check to see if the username already exists.
     *
     * @param username
     *            The username to check if is valid.
     * @return boolean Whether the username is valid or not.
     */
     public final boolean isValidUsername(final String username) {
        boolean retVal = true;
        final ArrayList<User> userList = adapt.getUsers();
        for (final User user : userList) {
            if (user.getUsername().equals(username)) {
                retVal = false;
            }
        }
        return retVal;
        }

     /**
      * Gets all the info for a specified user.
      * @param username The username to look up
      * @return The user's information or null if doesn't exist
      */
    public final User getUserInfo(final String username) {
        final ArrayList<User> userlist = adapt.getUsers();
        for (final User user : userlist) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Checks the info to the passed information.
     *
     * @param user
     *            The user to check if valid.
     * @return Returns boolean based on login.
     */
    public final boolean checkLogin(final User user) {
        final ArrayList<User> users = adapt.getUsers();

        return users.contains(user);
    }

    /**
     * Adds a New User.
     *
     * @param user
     *            The user to add.
     */

    public final void add(final User user) {
        adapt.addUser(user);
    }

    /**
     * Deletes the user from the database.
     *
     * @param user
     *            The user to delete.
     */
    public final void delete(final User user) {
        adapt.deleteUser(user);
    }

    /**
     * Returns the User List.
     *
     *@return The arraylist of all users.
     */
    public final ArrayList<User> getUserList() {
        return adapt.getUsers();

    }
    
    /**
     * Gets all the accounts for a user.
     *
     * @param user
     *            User to get accounts for.
     * @return the accounts.
     */
    public final ArrayList<Account> getAccountsForUser(final User user) {
        return adapt.getAccountsForUser(user);
    }

    /**
     * Creates an account for the given user.
     *
     * @param account
     *            The account.
     * @param user
     *            User to create the account for.
     */

    public final void createAccount(final Account account, final User user) {
        adapt.addAccountToUser(account.getName(), user);
    }

    /**
     * Deletes the account for the given user.
     *
     * @param name
     *            Name of the account to delete.
     * @param user
     *            User that owns the account.
     */

    public final void deleteAccount(final String name, final User user) {
        adapt.deleteAccount(name, user);
    }

    /**
     * Adds a transaction to an account.
     *
     * @param transaction
     *            Transaction to add.
     * @param account
     *            Account to own transaction.
     * @param user
     *            User that owns account.
     */

    public final void addTransactiontoAccount(final Transaction transaction,
        final Account account, final User user) {
        adapt.addTransactionToAccount(transaction, account, user);
    }

    /**
     * Gets all transactions for a given account.
     *
     * @param account
     *            Account owning transactions.
     * @param user
     *            User owning account.
     * @return all transactions for an account.
     */

    public final ArrayList<Transaction> getTransactionsForAccount(
       final Account account, final User user) {
       return adapt.getTransactionsForAccount(account, user);
    }

    /**
     * Computes the balance for a given account.
     *
     * @param account Account to calculate for.
     * @param user Use who owns the account.
     * @return balance
     */

    public final int getBalanceForAccount(final Account account,
        final User user) {
        final ArrayList<Transaction> trans =
            getTransactionsForAccount(account, user);
        int balance = 0;
        for (final Transaction transaction : trans) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    /**
     * Deletes a transaction.
     *
     * @param transaction
     *            Transaction to delete.
     * @param account
     *            Account owning the transaction.
     * @param user
     *            User who owns the account.
     */

    public final void deleteTransaction(final Transaction transaction,
        final Account account, final User user) {
        adapt.deleteTransaction(transaction, account, user);
    }

    /**
     * Generates the spending category report.
     *
     * @param user The user owning the report.
     * @param start The start date for the report.
     * @param end The end date for the report.
     * @return The final spending category report.
     */

    public final SpendingCategoryReport generateSpendingCategoryReport(
        final User user, final GregorianCalendar start,
            final GregorianCalendar end) {
        final ArrayList<Account> accounts = adapt.getAccountsForUser(user);
        final ArrayList<Transaction> trans = new ArrayList<Transaction>();
        for (final Account account : accounts) {
            final ArrayList<Transaction> temp = adapt
                 .getTransactionsForAccount(account, user);
        for (final Transaction tran : temp) {
             if (start.getTimeInMillis()
                 - tran.getDate().getTimeInMillis() <= 0
         && end.getTimeInMillis() - tran.getDate()
                .getTimeInMillis() >= 0) {
                    trans.add(tran);
                }
            }
        }
        final SpendingCategoryReport report = new SpendingCategoryReport(trans);

        return report;
    }

    /**
     * Closes the adapter.
     */
    public final void closeAdapt() {
        adapt.close();
    }
}
