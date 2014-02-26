package com.mikesandfriends.cashflow.database;

import java.util.ArrayList;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.Transaction;
import com.mikesandfriends.cashflow.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
* Allows database interaction
*
* @author Alec Lombardo
* @version 1.0
*
*/
public class CashFlowDBAdapter {
	private static final String KEY_USERNAME = "username";
	private static final String KEY_USERPASSWORD = "password";
	private static final String KEY_ACCOUNTNAME = "name";
	private static final String KEY_ACCOUNTOWNER = "owner";
	private static final String KEY_TRANSACTIONNAME = "transName";
	private static final String KEY_TRANSACTIONAMOUNT = "transAmount";
	private static String databaseName;
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "CashFlowDBAdapter";
	private static String usersCreate;
	private static String accountsCreate;
	private static String transactionsCreate;
	@SuppressWarnings("unused")
	private  Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	/**
	* Adapter Constructor
	* 
	* @param context access context
	*/
	@SuppressWarnings("static-access")
	public CashFlowDBAdapter (Context context) {
		this.context = context;
		this.databaseName = "CashFlowDB";
		DBHelper = new DatabaseHelper(context);
	}
	
	//opens the database
	/**
	* Opens the Database
	* @return returns the address of the database location for writing
	*/
	public CashFlowDBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	/**
	* Closes the Database
	*/
	public void close() {
		DBHelper.close();
	}
	
	/**
	* Rebuilds the Database
	*/
	public void rebuild() {
		DBHelper.onCreate(db);
	}
	
	
	
	
    /** 
    * Writes a user in the database
    * @param user user to be written
    */
	public void addUser(User user) {
		ContentValues values;
		values = new ContentValues();
		values.put(KEY_USERNAME, user.getUsername());
		values.put(KEY_USERPASSWORD, user.getPassword());
		db.insert("users", null, values);
	}
	
	/** 
	* Gets all user
	* @return returns all users
	*/
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();

		String[] columns = {KEY_USERNAME, KEY_USERPASSWORD};
		Cursor cursor = db.query("users", columns, null, null, null, null, null);
		
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			User user = new User(cursor.getString(0), cursor.getString(1));
			cursor.moveToNext();
			users.add(user);
		}
		
		return users;
	}
	/**
	* Deletes a user
	* @param user user to be deleted
	*/
	public void deleteUser(User user) {
		String[] whereArgs = {user.getUsername()}; 
		db.delete("users", KEY_USERNAME + "=?", whereArgs);
	}
	
	/**
	 * Adds an account to a user
	 * @param name Name of the account
	 * @param user User to add the account to
	 */
	public void addAccountToUser(String name, User user) {
		ContentValues values;
		values = new ContentValues();
		values.put(KEY_ACCOUNTNAME, name);
		values.put(KEY_ACCOUNTOWNER, user.getUsername());
		db.insert("accounts", null, values);	
	}
	
	/**
	 * Gets a list of accounts for a user
	 * @param user User to find accounts for
	 * @return All accounts associated with user
	 */
	public ArrayList<Account> getAccountsForUser(User user) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		String[] columns = {KEY_ACCOUNTNAME, KEY_ACCOUNTOWNER};
		Cursor cursor = db.query("accounts", columns, null, null, null, null, null);
		
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			if (cursor.getString(1).equals(user.getUsername())) {
				accounts.add(new Account(cursor.getString(0)));
			}
			cursor.moveToNext();
		}
		
		return accounts;
	}
	
	/**
	 * Deletes an account
	 * @param name Name of the account to delete
	 * @param user User who owns the account
	 */
	public void deleteAccount(String name, User user) {
		String[] whereArgs = {name, user.getUsername()};
		db.delete("accounts", KEY_ACCOUNTNAME + "=? AND "
				+ KEY_ACCOUNTOWNER + "=?", whereArgs);
	}
	
	/**
	 * Adds a transaction to a user's account
	 * @param transaction transaction to be added
	 * @param account account to add transaction to
	 * @param user user who owns the account
	 */
	public void addTransactionToAccount(Transaction transaction,
			Account account, User user) {
		ContentValues values;
		values = new ContentValues();
		values.put(KEY_ACCOUNTNAME, account.getName());
		values.put(KEY_ACCOUNTOWNER, user.getUsername());
		values.put(KEY_TRANSACTIONNAME, transaction.getName());
		values.put(KEY_TRANSACTIONAMOUNT,
				Integer.toString(transaction.getAmount()));
		db.insert("transactions", null, values);	
	}
	
	/**
	 * Returns all transactions for a given user account
	 * @param account Account owning the transactions
	 * @param user User owning the account
	 * @return transactions for the account
	 */
	public ArrayList<Transaction> getTransactionsForAccount(Account account,
			User user) {
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		String[] columns = {KEY_ACCOUNTNAME, KEY_ACCOUNTOWNER,
				KEY_TRANSACTIONAMOUNT, KEY_TRANSACTIONNAME};
		Cursor cursor = db.query("transactions", columns, null, null, null,
				null, null);
		
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			if (cursor.getString(1).equals(user.getUsername()) &&
					cursor.getString(0).equals(account.getName())) {
				trans.add(new Transaction(cursor.getString(3),
						Integer.parseInt(cursor.getString(2))));
			}
			cursor.moveToNext();
		}
		return trans;
	}
	
	/**
	 * Deletes a transaction
	 * @param transaction the transaction to delete
	 * @param account the account owning the transaction
	 * @param user User who owns the account
	 */
	public void deleteTransaction(Transaction transaction, Account account,
			User user) {
		String[] whereArgs = {transaction.getName(),
				Integer.toString(transaction.getAmount()), account.getName(),
				user.getUsername()};
		db.delete("transactions", KEY_TRANSACTIONNAME + "=? AND "
				+ KEY_TRANSACTIONAMOUNT + "=? AND "	+ KEY_ACCOUNTNAME
				+ "=? AND " + KEY_USERNAME + "=?", whereArgs);
	}
	
	/**
	* Supplementary Database helper class
	* @author Alec Lombardo 
	* @version 1.0
	*/
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, databaseName, null, DATABASE_VERSION);
			usersCreate = "CREATE TABLE " + "users" + " (" + KEY_USERNAME + " TEXT, "
								+ KEY_USERPASSWORD + " TEXT);";
			accountsCreate = "CREATE TABLE " + "accounts" + " ("
								+ KEY_ACCOUNTOWNER + " TEXT, " + KEY_ACCOUNTNAME
								+ " TEXT);";
			transactionsCreate = "CREATE TABLE " + "transactions" + " ("
								+ KEY_ACCOUNTOWNER + " TEXT, " + KEY_ACCOUNTNAME
								+ " TEXT, " + KEY_TRANSACTIONNAME + " TEXT, "
								+ KEY_TRANSACTIONAMOUNT + " TEXT);";
		
		}
		
		@Override
		/** 
		* Error testing for database access
		* @param db database to be tested
		*/
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(usersCreate);
				db.execSQL(accountsCreate);
				db.execSQL(transactionsCreate);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		/**
		* Upgrades database tables
		* @param db database address 
		* @param oldVersion old version number
		* @param newVersion new version number 
		*/
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database will destroy all data in the table");
			db.execSQL("DROP TABLE IF EXISTS " + "users");
			db.execSQL("DROP TABLE IF EXISTS accounts");
			db.execSQL("DROP TABLE IF EXISTS transactions");
			onCreate(db);
		}		
	}
}
