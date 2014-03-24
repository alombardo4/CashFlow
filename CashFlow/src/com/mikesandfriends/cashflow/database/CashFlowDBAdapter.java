package com.mikesandfriends.cashflow.database;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.SpendingCategory;
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
* Allows database interaction.
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
	private static final String KEY_TRANSNAME = "transName";
	private static final String KEY_TRANSAMOUNT = "transAmount";
	private static final String KEY_TRANSCAT = "transCat";
	private static final String KEY_TRANSDATE = "transDate";
	private static final String DATABASE_NAME = "CashFlowDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "CashFlowDBAdapter";
	private static final String USERS_CREATE = "CREATE TABLE " + "users" + " ("
			+ KEY_USERNAME + " TEXT, " + KEY_USERPASSWORD + " TEXT);";
	private static final String ACCOUNTS_CREATE = "CREATE TABLE " + "accounts" 
			+ " ("	+ KEY_ACCOUNTOWNER + " TEXT, "
			+ KEY_ACCOUNTNAME + " TEXT);";
	private static final String TRANS_CREATE = "CREATE TABLE " + "transactions"
			+ " ("	+ KEY_ACCOUNTOWNER + " TEXT, " + KEY_ACCOUNTNAME
			+ " TEXT, " + KEY_TRANSNAME + " TEXT, "
			+ KEY_TRANSAMOUNT + " TEXT, "
			+ KEY_TRANSCAT + " INTEGER, "
			+ KEY_TRANSDATE + " INTEGER);";
	private  Context context;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	/**
	* Adapter Constructor
	* 
	* @param context access context
	*/
	public CashFlowDBAdapter(final Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	/**
	* Opens the Database.
	* @return returns the address of the database location for writing.
	*/
	public final CashFlowDBAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	/**
	* Closes the Database.
	*/
	public final void close() {
		dbHelper.close();
	}
	
	/**
	* Rebuilds the Database.
	*/
	public final void rebuild() {
		dbHelper.onCreate(db);
	}
	
	
	
	
    /** 
    * Writes a user in the database.
    * @param user user to be written
    */
	public final void addUser(final User user) {
		ContentValues values;
		values = new ContentValues();
		values.put(KEY_USERNAME, user.getUsername());
		values.put(KEY_USERPASSWORD, user.getPassword());
		db.insert("users", null, values);
	}
	
	/** 
	* Gets all users.
	* @return returns all users
	*/
	public final ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();

		String[] columns = {KEY_USERNAME, KEY_USERPASSWORD};
		Cursor cursor = db.query("users", columns, null, null, null, null,
				null);
		
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			User user = new User(cursor.getString(0), cursor.getString(1));
			cursor.moveToNext();
			users.add(user);
		}
		
		return users;
	}
	/**
	* Deletes a user.
	* @param user user to be deleted
	*/
	public final void deleteUser(final User user) {
		String[] whereArgs = {user.getUsername()}; 
		db.delete("users", KEY_USERNAME + "=?", whereArgs);
	}
	
	/**
	 * Adds an account to a user.
	 * @param name Name of the account
	 * @param user User to add the account to
	 */
	public final void addAccountToUser(final String name, final User user) {
		ContentValues values;
		values = new ContentValues();
		values.put(KEY_ACCOUNTNAME, name);
		values.put(KEY_ACCOUNTOWNER, user.getUsername());
		db.insert("accounts", null, values);	
	}
	
	/**
	 * Gets a list of accounts for a user.
	 * @param user User to find accounts for
	 * @return All accounts associated with user
	 */
	public final ArrayList<Account> getAccountsForUser(final User user) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		String[] columns = {KEY_ACCOUNTNAME, KEY_ACCOUNTOWNER};
		Cursor cursor = db.query("accounts", columns, null, null, null, null,
				null);
		
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
	 * Deletes an account.
	 * @param name Name of the account to delete
	 * @param user User who owns the account
	 */
	public final void deleteAccount(final String name, final User user) {
		String[] whereArgs = {name, user.getUsername()};
		db.delete("accounts", KEY_ACCOUNTNAME + "=? AND "
				+ KEY_ACCOUNTOWNER + "=?", whereArgs);
	}
	
	/**
	 * Adds a transaction to a user's account.
	 * @param transaction transaction to be added
	 * @param account account to add transaction to
	 * @param user user who owns the account
	 */
	public final void addTransactionToAccount(final Transaction transaction,
			final Account account, final User user) {
		ContentValues values;
		values = new ContentValues();
		values.put(KEY_ACCOUNTNAME, account.getName());
		values.put(KEY_ACCOUNTOWNER, user.getUsername());
		values.put(KEY_TRANSNAME, transaction.getName());
		values.put(KEY_TRANSAMOUNT,
				Integer.toString(transaction.getAmount()));
		values.put(KEY_TRANSCAT,
				transaction.getCategory().ordinal());
		values.put(KEY_TRANSDATE,
				transaction.getDate().getTimeInMillis());
		
		db.insert("transactions", null, values);	
	}
	
	/**
	 * Returns all transactions for a given user account.
	 * @param account Account owning the transactions
	 * @param user User owning the account
	 * @return transactions for the account
	 */
	public final ArrayList<Transaction> getTransactionsForAccount(
			final Account account, final User user) {
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		final String[] columns = {KEY_ACCOUNTNAME, KEY_ACCOUNTOWNER,
				KEY_TRANSAMOUNT, KEY_TRANSNAME,
				KEY_TRANSCAT, KEY_TRANSDATE};
		Cursor cursor = db.query("transactions", columns, null, null,
				null, null, null);
		
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			if (cursor.getString(1).equals(user.getUsername())
					&& cursor.getString(0).equals(account.getName())) {
				GregorianCalendar date = new GregorianCalendar();
				date.setTimeInMillis(cursor.getLong(5));
				
				int cat = cursor.getInt(4);
				
				Transaction temp = new Transaction(cursor.getString(3),
						Integer.parseInt(cursor.getString(2)),
						SpendingCategory.values()[cat],	date);
				SpendingCategory.values();
				trans.add(temp);
			}
			cursor.moveToNext();
		}
		return trans;
	}
	
	/**
	 * Deletes a transaction.
	 * @param transaction the transaction to delete
	 * @param account the account owning the transaction
	 * @param user User who owns the account
	 */
	public final void deleteTransaction(final Transaction transaction,
			final Account account, final User user) {
		String[] whereArgs = {transaction.getName(),
				Integer.toString(transaction.getAmount()), account.getName(),
				user.getUsername()};
		db.delete("transactions", KEY_TRANSNAME + "=? AND "
				+ KEY_TRANSAMOUNT + "=? AND "	+ KEY_ACCOUNTNAME
				+ "=? AND " + KEY_USERNAME + "=?", whereArgs);
	}
	
	/**
	* Supplementary Database helper class.
	* @author Alec Lombardo 
	* @version 1.0
	*/
	private static class DatabaseHelper extends SQLiteOpenHelper {
		/**
		 * Constructor for DatabaseHelper.
		 * @param context The application context
		 */
		public DatabaseHelper(final Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);		
		}
		
		@Override
		/** 
		* Error testing for database access
		* @param db database to be tested
		*/
		public void onCreate(final SQLiteDatabase db) {
			try {
				db.execSQL(USERS_CREATE);
				db.execSQL(ACCOUNTS_CREATE);
				db.execSQL(TRANS_CREATE);
			} catch (SQLException e) {
				Log.w(TAG, "Could not create database.");
			}
		}
		
		@Override
		/**
		* Upgrades database tables
		* @param db database address 
		* @param oldVersion old version number
		* @param newVersion new version number 
		*/
		public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
				final int newVersion) {
			Log.w(TAG, "Upgrading database will destroy all data in the table");
			db.execSQL("DROP TABLE IF EXISTS " + "users");
			db.execSQL("DROP TABLE IF EXISTS accounts");
			db.execSQL("DROP TABLE IF EXISTS transactions");
			onCreate(db);
		}		
	}
}
