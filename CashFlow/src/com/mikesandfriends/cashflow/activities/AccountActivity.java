package com.mikesandfriends.cashflow.activities;

import java.util.ArrayList;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.User;
import com.mikesandfriends.cashflow.database.UserDataHandler;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Tells Lint to ignore warnings for this annotation.
 *
 */
@SuppressLint("CutPasteId")

public class AccountActivity extends Activity {
    /**
     * The array holding the accounts.
     */
    private ArrayList<Account> items;
    /**
     * The UserDataHandler used to interact with the database.
     */
    private UserDataHandler udh;
    /**
     * The current user.
     */
    private User user;
    /**
     * The array adapter used to hold the names of items.
     */
    private ArrayAdapter<String> arrayAdapter;
    @Override
    /**.
     * The activity that displays all of the accounts,
     * allows for new account creation,
     * and can show reports
     *
     * @param bundle with the current user and their information
     */
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getActionBar().setTitle("Accounts");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));
        user = (User) getIntent().getExtras().getSerializable("user");
        Button newAccount = (Button) findViewById(R.id.newaccount);
        ListView accountsList = (ListView) findViewById(R.id.accountslist);
        newAccount.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getBaseContext(),
                        NewAccountActivity.class);
                i.putExtras(getIntent().getExtras());
                startActivity(i);
            }
        });
        udh = new UserDataHandler(getBaseContext());
        items = udh.getAccountsForUser(user);
        udh.closeAdapt();
        arrayAdapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1);
        for (Account item : items) {
            arrayAdapter.add(item.getName());
        }
        accountsList.setAdapter(arrayAdapter);

        ListView listview = (ListView) findViewById(R.id.accountslist);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent,
                    final View  view, final int position, final long id) {
                    Account account = items.get(position);
                    String indexTag = account.getName();
                    Intent i = new Intent(getBaseContext(),
                            MyAccountActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("accountName", indexTag);
                    b.putSerializable("user", user);
                    i.putExtras(b); //"accountName", indexTag);
                    //(String) listview.getItemAtPosition(position));
                    startActivity(i);
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
        menu.add(0, 0, 1, "Spending Category Report").
        setIcon(R.drawable.ic_action_spendingcategoryreport).
        setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == 0) {
            Intent i = new Intent(getBaseContext(),
                    SpendingCategoryReportActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("user", user);
            i.putExtras(b);
            startActivity(i);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //Intentionally does nothing to prevent accidental logout
    }

}
