package com.mikesandfriends.cashflow;

import java.util.ArrayList;
import android.content.Context;

/**
* Check the Login against the database
*
* @author Michael Avery
* @version 1.0
*
*/
public class LoginHandler {
	
	private CashFlowDBAdapter adapt;
	
	/**
	 * Login Check constructor
	 * @param context
	 */
	public LoginHandler(Context context) {
		adapt = new CashFlowDBAdapter(context);
		adapt.open();
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
	* Closes the adapter
	* @param none
	* @return none
	*/
	public void closeAdapt() {
		adapt.close();
	
	}
	
}