package com.mikesandfriends.cashflow.database;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
 * Allows database interaction.
 *
 * @author Alec Lombardo
 * @version 1.0
 *
 */
public class CashFlowDBAdapter { //all methods are needed for functionality
    /**
     * String representing a User's username.
     */
    private static final String KEY_USERNAME = "username";
    /**
     * String representing a User's password.
     */
    private static final String KEY_USERPASSWORD = "password";
    /**
     * String representing a User's account name.
     */
    private static final String KEY_ACCOUNTNAME = "name";
    /**
     * String representing the account's owner.
     */
    private static final String KEY_ACCOUNTOWNER = "owner";
    /**
     * String representing the transaction name.
     */
    private static final String KEY_TRANSNAME = "transName";
    /**
     * String representing the amount in a transaction.
     */
    private static final String KEY_TRANSAMOUNT = "transAmount";
    /**
     * String representing the category for a transaction.
     */
    private static final String KEY_TRANSCAT = "transCat";
    /**
     * String representing the date of a transaction.
     */
    private static final String KEY_TRANSDATE = "transDate";
    /**
     * String representing the name of this database.
     */
    private static final String DATABASE_NAME = "CashFlowDB";
    /**
     * Int representing the version of the database.
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * String representing the tag for the Adapter.
     */
    private static final String TAG = "CashFlowDBAdapter";
    /**
     * String representing the word users.
     */
    private static final String USERS = "users";
    /**
     * String representation of the word TEXT, .
     */
    private static final String TEXT = " TEXT, ";
    /**
     * String representation of word accounts.
     */
    private static final String ACCOUNTS = "accounts";
    /**
     * String representation of =?.
     */
    private static final String EQUALS = "=?";
    /**
     * String representation of word transactions.
     */
    private static final String TRANSACTIONS = "transactions";
    /**
     * String representation of =? AND.
     */
    private static final String EQUALSAND = "=? AND ";
    /**
     * String representing the table of users, usernames and passwords.
     */
    private static final String USERS_CREATE = "CREATE TABLE " + USERS + " ("
            + KEY_USERNAME + TEXT + KEY_USERPASSWORD + " TEXT);";
    /**
     * String representing the table of accounts, owners, and names.
     */
    private static final String ACCOUNTS_CREATE = "CREATE TABLE " + ACCOUNTS
            + " (" + KEY_ACCOUNTOWNER + TEXT + KEY_ACCOUNTNAME + " TEXT);";
    /**
     * String representing the table of transactions, account names,
     * account owners, and various transaction details.
     */
    private static final String TRANS_CREATE = "CREATE TABLE " + TRANSACTIONS
            + " (" + KEY_ACCOUNTOWNER + TEXT + KEY_ACCOUNTNAME + TEXT
            + KEY_TRANSNAME + TEXT + KEY_TRANSAMOUNT + TEXT
            + KEY_TRANSCAT + " INTEGER, " + KEY_TRANSDATE + " INTEGER);";
    /**
     * Object that assists in database-related actions.
     */
    private final DatabaseHelper dbHelper;
    /**
     * Database obtained by the DatabaseHelper object.
     */
    private SQLiteDatabase sqldb;

    /**
     * int of value 5 used later in obtaining the Long for the calendar.
     */
    private static final int FIVE = 5;
    /**
     * int of value 4 used later in obtaining the category.
     */
    private static final int FOUR = 4;
    /**
     * int of value 3 used later in obtaining a String for the transaction.
     */
    private static final int THREE = 3;

    /**
     * Adapter Constructor.
     *
     * @param pcontext
     *            access context
     */
    public CashFlowDBAdapter(final Context pcontext) {
        dbHelper = new DatabaseHelper(pcontext);
    }

    /**
     * Opens the Database.
     *
     * @return returns the address of the database location for writing.
     */
    public final CashFlowDBAdapter open() {
        sqldb = dbHelper.getWritableDatabase();
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
        dbHelper.onCreate(sqldb);
    }

    /**
     * Writes a user in the database.
     *
     * @param user
     *            user to be written
     */
    public final void addUser(final User user) {
        ContentValues values;
        values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_USERPASSWORD, user.getPassword());
        sqldb.insert(USERS, null, values);
    }

    /**
     * Gets all users.
     *
     * @return returns all users
     */
    public final ArrayList<User> getUsers() {
        final ArrayList<User> users = new ArrayList<User>();

        final String[] columns = {KEY_USERNAME, KEY_USERPASSWORD};
        final Cursor cursor = sqldb
                .query(USERS, columns, null, null, null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            //variable number of users
            final User user = new User(cursor.getString(0),
                cursor.getString(1));
            cursor.moveToNext();
            users.add(user);
        }

        return users;
    }

    /**
     * Deletes a user.
     *
     * @param user
     *            user to be deleted
     */
    public final void deleteUser(final User user) {
        final String[] whereArgs = {user.getUsername()};
        sqldb.delete(USERS, KEY_USERNAME + EQUALS, whereArgs);
    }

    /**
     * Adds an account to a user.
     *
     * @param name
     *            Name of the account
     * @param user
     *            User to add the account to
     */
    public final void addAccountToUser(final String name, final User user) {
        ContentValues values;
        values = new ContentValues();
        values.put(KEY_ACCOUNTNAME, name);
        values.put(KEY_ACCOUNTOWNER, user.getUsername());
        sqldb.insert(ACCOUNTS, null, values);
    }

    /**
     * Gets a list of accounts for a user.
     *
     * @param user
     *            User to find accounts for
     * @return All accounts associated with user
     */
    public final ArrayList<Account> getAccountsForUser(final User user) {
        final ArrayList<Account> accounts = new ArrayList<Account>();
        final String[] columns = {KEY_ACCOUNTNAME, KEY_ACCOUNTOWNER};
        final Cursor cursor = sqldb.query(ACCOUNTS, columns, null, null, null,
            null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.getString(1).equals(user.getUsername())) {
                //variable number of accounts, need loop
                final Account temp = new Account(cursor.getString(0));
                accounts.add(temp);
            }
            cursor.moveToNext();
        }

        return accounts;
    }

    /**
     * Deletes an account.
     *
     * @param name
     *            Name of the account to delete
     * @param user
     *            User who owns the account
     */
    public final void deleteAccount(final String name, final User user) {
        final String[] whereArgs = {name, user.getUsername()};
        sqldb.delete(ACCOUNTS, KEY_ACCOUNTNAME + EQUALSAND + KEY_ACCOUNTOWNER
                + EQUALS, whereArgs);
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
        values.put(KEY_TRANSAMOUNT, Integer.toString(transaction.getAmount()));
        values.put(KEY_TRANSCAT, transaction.getCategory());
        values.put(KEY_TRANSDATE, transaction.getDate().getTimeInMillis());

        sqldb.insert(TRANSACTIONS, null, values);
    }

    /**
     * Returns all transactions for a given user account.
     * @param account Account owning the transactions
     * @param user User owning the account
     * @return transactions for the account
     */
    public final ArrayList<Transaction> getTransactionsForAccount(
            final Account account, final User user) {
        final ArrayList<Transaction> trans = new ArrayList<Transaction>();
        final String[] columns = {KEY_ACCOUNTNAME, KEY_ACCOUNTOWNER,
                KEY_TRANSAMOUNT, KEY_TRANSNAME, KEY_TRANSCAT, KEY_TRANSDATE };
        final Cursor cursor = sqldb.query(TRANSACTIONS, columns, null, null,
            null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.getString(1).equals(user.getUsername())
                    && cursor.getString(0).equals(account.getName())) {
                //need a new one each time
                final GregorianCalendar date = new GregorianCalendar();
                date.setTimeInMillis(cursor.getLong(FIVE));

                final int cat = cursor.getInt(FOUR);
                //need to have variable number of Transactions
                final Transaction temp = new Transaction(
                    cursor.getString(THREE),
                    Integer.parseInt(cursor.getString(2)), cat, date);
                trans.add(temp);
            }
            cursor.moveToNext();
        }
        return trans;
    }

    /**
     * Deletes a transaction.
     *
     * @param transaction the transaction to delete
     * @param account the account owning the transaction
     * @param user User who owns the account
     */
    public final void deleteTransaction(final Transaction transaction,
            final Account account, final User user) {
        final String[] whereArgs = {transaction.getName(),
                Integer.toString(transaction.getAmount()), account.getName(),
                user.getUsername() };
        sqldb.delete(TRANSACTIONS,
                KEY_TRANSNAME + EQUALSAND + KEY_TRANSAMOUNT + EQUALSAND
                        + KEY_ACCOUNTNAME + EQUALSAND + KEY_USERNAME + EQUALS,
                whereArgs);
    }

    /**
     * Supplementary Database helper class.
     *
     * @author Alec Lombardo
     * @version 1.0
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        /**
         * Constructor for DatabaseHelper.
         *
         * @param context
         *            The application context
         */
        public DatabaseHelper(final Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        /**
         * Error testing for database access
         * @param sqldb database to be tested
         */
        public void onCreate(final SQLiteDatabase sqldb) {
            try {
                sqldb.execSQL(USERS_CREATE);
                sqldb.execSQL(ACCOUNTS_CREATE);
                sqldb.execSQL(TRANS_CREATE);
            } catch (SQLException e) {
                Log.w(TAG, "Could not create database.");
            }
        }

        @Override
        /**
         * Upgrades database tables
         * @param sqldb database address
         * @param oldVersion old version number
         * @param newVersion new version number
         */
        public void onUpgrade(final SQLiteDatabase sqldb, final int oldVersion,
                final int newVersion) {
            Log.w(TAG, "Upgrading database will destroy all data in the table");
            sqldb.execSQL("DROP TABLE IF EXISTS " + USERS);
            sqldb.execSQL("DROP TABLE IF EXISTS accounts");
            sqldb.execSQL("DROP TABLE IF EXISTS transactions");
            onCreate(sqldb);
        }
    }
}
