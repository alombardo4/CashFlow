package com.mikesandfriends.cashflow;

import java.util.ArrayList;
import android.content.Context;

/**
* Handles getting all data for a user from the database
*
* @author Michael Avery
* @version 1.0
*
*/
public class UserDataHandler {
	
	private CashFlowDBAdapter adapt;
	
	/**
	 * Login Check constructor
	 * @param context
	 */
	public UserDataHandler(Context context) {
		adapt = new CashFlowDBAdapter(context);
		adapt.open();
		User admin = new User("admin", "pass1234");
		if (!checkLogin(admin)) {
			add(admin);
		}
	}
	
	/**
	 * Check to see if the username already exists
	 * @return boolean 
	 */
	public boolean isValidUsername(String username){
		
		ArrayList<User> userList = adapt.getUsers();		
		for(User user : userList){
			if(user.getUsername().toString().equals(username.toString()))
				return false;
		}
		return true;
	}
	

	/** 
	* Checks the info to the passed information
	* @return returns boolean based on login
	*/
	public boolean checkLogin(User user) {
		ArrayList<User> users = adapt.getUsers();
		
		return users.contains(user);
	}
	
	/**
	 * Adds a New User
	 * @param user
	 */
	public void add(User user) {
		adapt.addUser(user);
	}
	
	/**
	 * Deletes the user from the database
	 * @param user
	 */
	public void delete(User user) {
		adapt.deleteUser(user);
	}
	
	/**
	 * Returns the User List
	 * @return
	 */
	public ArrayList<User> getUserList() {
		return adapt.getUsers();
		
	}
	/**
	 * Gets all the accounts for a user
	 * @param user User to get accounts for
	 * @return the accounts
	 */
	public ArrayList<Account> getAccountsForUser(User user) {
		return adapt.getAccountsForUser(user);
	}
	
	/**
	 * Creates an account for the given user
	 * @param name Name of the account
	 * @param user User to create the account for
	 */
	public void createAccount(Account account, User user) {
		adapt.addAccountToUser(account.getName(), user);
	}
	
	/**
	 * Deletes the account for the given user
	 * @param name Name of the account to delete
	 * @param user User that owns the account
	 */
	public void deleteAccount(String name, User user) {
		adapt.deleteAccount(name, user);
	}
	
	/**
	 * Adds a transaction to an account
	 * @param transaction Transaction to add
	 * @param account Account to own transaction
	 * @param user User that owns account
	 */
	public void addTransactiontoAccount(Transaction transaction,
			Account account, User user) {
		adapt.addTransactionToAccount(transaction, account, user);
	}
	
	/**
	 * Gets all transactions for a given account
	 * @param account Account owning transactions
	 * @param user User owning account
	 * @return all transactions for an account
	 */
	public ArrayList<Transaction> getTransactionsForAccount(Account account,
			User user) {
		return adapt.getTransactionsForAccount(account, user);
	}
	
	/**
	 * Computes the balance for a given account
	 * @param account Account to calculate for
	 * @param user Use who owns the account
	 * @return balance
	 */
	public int getBalanceForAccount(Account account, User user) {
		ArrayList<Transaction> trans = getTransactionsForAccount(account, user);
		int balance = 0;
		for (Transaction transaction : trans) {
			balance += transaction.getAmount();
		}
		return balance;
	}
	
	/**
	 * Deletes a transaction
	 * @param transaction Transaction to delete
	 * @param account Account owning the transaction
	 * @param user User who owns the account
	 */
	public void deleteTransaction(Transaction transaction, Account account,
			User user) {
		adapt.deleteTransaction(transaction, account, user);
	}
	
	/** 
	* Closes the adapter
	* @param none
	* @return none
	*/
	public void closeAdapt() {
		adapt.close();
	
	}
	
}