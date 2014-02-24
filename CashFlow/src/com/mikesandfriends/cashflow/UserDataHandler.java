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
	* Closes the adapter
	* @param none
	* @return none
	*/
	public void closeAdapt() {
		adapt.close();
	
	}
	
}