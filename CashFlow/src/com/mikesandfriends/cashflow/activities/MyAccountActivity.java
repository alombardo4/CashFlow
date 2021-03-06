package com.mikesandfriends.cashflow.activities;

import java.util.GregorianCalendar;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.Transaction;
import com.mikesandfriends.cashflow.User;
import com.mikesandfriends.cashflow.database.UserDataHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**.
 * Activity that handles a user's account
 * and displays appropriate information
 * and options for the user
 *
 * @author Chelsea
 *
 */

public class MyAccountActivity extends Activity {

   /**
    * String that holds the account ID (name).
    */
    private String ids;
    /**
     * The user associated with this account.
     */
    private User user;
    /**
     * The UserDataHandler object used to interact with the database.
     */
    private UserDataHandler udl;
    /**
     * Object that displays the account's balance on-screen.
     */
    private TextView balanceOnScreen;
    /**
     *Object that allows for selecting dates to put with transactions.
     */
    private DatePicker date;
    /**
     *Object to determine which spending category this falls into.
     */
    private Spinner categorySpinner;
    /**
     *String Array of spending categories from which to choose.
     */
    private final String[] categories = {"Food", "Rent", "Clothing",
            "Entertainment" };
    /**
     * int of value 4 for the categories.
     */
    private static final int CAT4 = 4;
    /**
     * int of value 3 for the categories.
     */
    private static final int CAT3 = 3;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        ids = (String) getIntent().getSerializableExtra("accountName");

        getActionBar().setTitle(ids);
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#035986")));

        Button addButton = (Button) findViewById(R.id.addButton);
        Button minusButton = (Button) findViewById(R.id.minusButton);
        date = (DatePicker) findViewById(R.id.transactiondate);
        categorySpinner = (Spinner) findViewById(R.id.categoryspinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        categorySpinner.setAdapter(spinnerAdapter);

        udl = new UserDataHandler(getBaseContext());
        user = (User) getIntent().getSerializableExtra("user");

        // Display the Balance on Create
        int balance = udl.getBalanceForAccount(new Account(ids), user);
        balanceOnScreen = (TextView) findViewById(R.id.accountBalance);
        balanceOnScreen.setText("Balance: $" + balance);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int balance = udl.getBalanceForAccount(new Account(ids), user);
                EditText transAmount = (EditText)
                        findViewById(R.id.transactionAmount);
                EditText description = (EditText)
                        findViewById(R.id.description);
                if (description.getText().toString().length() >= 1
                        && transAmount.getText().toString().length() >= 1) {
                    int money = Integer.parseInt(transAmount.getText()
                            .toString());
                    int afterTrans = balance + money;
                    balanceOnScreen.setText("Balance: $" + afterTrans);
                    GregorianCalendar day = new GregorianCalendar();
                    day.set(GregorianCalendar.DAY_OF_MONTH,
                            date.getDayOfMonth());
                    day.set(GregorianCalendar.MONTH, date.getMonth());
                    day.set(GregorianCalendar.YEAR, date.getYear());
                    int cat = 0;
                    final Transaction trans =
                            new Transaction(description.getText().toString(),
                            money, cat, day);
                    udl.addTransactiontoAccount(trans, new Account(ids), user);
                } else {
                    CharSequence text;
                    if (description.getText().toString().length() < 1) {
                        text = "Description must be at least "
                        + "1 character in length";
                    } else {
                        text = "Amount must be at least 1 character in length";
                    }
                    int duration = Toast.LENGTH_SHORT;
                    description.setText("");
                    transAmount.setText("");
                    Toast.makeText(getBaseContext(), text, duration).show();
                }
            }

        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int balance = udl.getBalanceForAccount(new Account(ids), user);
                EditText transAmount = (EditText)
                        findViewById(R.id.transactionAmount);
                EditText description = (EditText)
                        findViewById(R.id.description);
                if (description.getText().toString().length() >= 1
                        && transAmount.getText().toString().length() >= 1) {

                    int money = Integer.parseInt(transAmount.getText()
                            .toString());
                    int afterTrans = balance - money;
                    balanceOnScreen.setText("Balance: $" + afterTrans);
                    money = money - 2 * money;
                    final GregorianCalendar day = new GregorianCalendar();
                    day.set(GregorianCalendar.DAY_OF_MONTH,
                            date.getDayOfMonth());
                    day.set(GregorianCalendar.MONTH, date.getMonth());
                    day.set(GregorianCalendar.YEAR, date.getYear());
                    int cat = 0; //need to define so it compiles
                    //for the if's, that makes it unnecessarily complex code
                    if (categorySpinner.getSelectedItemPosition() == 0) {
                        cat = 1;
                    } else if (categorySpinner.getSelectedItemId() == 1) {
                        cat = CAT3;
                    } else if (categorySpinner.getSelectedItemId() == 2) {
                        cat = 2;
                    } else if (categorySpinner.getSelectedItemId() == CAT3) {
                        cat = CAT4;
                    }
                    final Transaction trans =
                            new Transaction(description.getText().toString(),
                            money, cat, day);
                    udl.addTransactiontoAccount(trans, new Account(ids), user);
                } else {
                    CharSequence text;
                    //makes it too complex if you remove the 1
                    if (description.getText().toString().length() < 1) {
                        text = "Description must be at"
                                + " least 1 character in length";
                    } else {
                        text = "Amount must be at least 1 character in length";
                    }
                    final int duration = Toast.LENGTH_SHORT;
                    description.setText("");
                    transAmount.setText("");
                    Toast.makeText(getBaseContext(), text, duration).show();
                }
            }
        });

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        createMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
    /**.
     * Creates the menu from which the user can choose
     * the Spending Category Report
     *
     * @param menu the menu from which to choose the report
     */

    private void createMenu(final Menu menu) {
        menu.add(0, 0, 1, "Transaction List").
        setIcon(R.drawable.ic_action_transactionlist).
        setTitle("Transaction List").
        setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }


    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == 0) { //android standard practice
            final Intent intent = new Intent(getBaseContext(),
                    TransactionsListActivity.class);
            final Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            bundle.putSerializable("accountName", ids);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return true;
    }

}
