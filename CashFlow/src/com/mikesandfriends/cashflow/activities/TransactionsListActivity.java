package com.mikesandfriends.cashflow.activities;

import java.util.ArrayList;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.Transaction;
import com.mikesandfriends.cashflow.User;
import com.mikesandfriends.cashflow.database.UserDataHandler;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * List of transactions for an account.
 * @author Alec Lombardo
 *
 */
public class TransactionsListActivity extends Activity {
    /**
     * The listview for the activity.
     */
    private ListView list;
    /**
     * The array adapter backing for the listview.
     */
    private ArrayAdapter<String> arrayAdapter;
    /**
     * User for the account.
     */
    private User user;
    /**
     * Account to look at.
     */
    private String account;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        getActionBar().setTitle("Transactions");
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#035986")));

        user = (User) getIntent().getExtras().getSerializable("user");
        account = (String)
                getIntent().getExtras().getSerializable("accountName");
        System.out.println(account);
        list = (ListView) findViewById(R.id.transactionsListView);
        UserDataHandler udh = new UserDataHandler(getBaseContext());
        Account acc = new Account(account);
        ArrayList<Transaction> items = udh.getTransactionsForAccount(acc, user);
        arrayAdapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1);

        for (final Transaction item : items) {
            arrayAdapter.add(item.getName() + ":    $" + item.getAmount());
        }
        arrayAdapter.notifyDataSetChanged();
        list.setAdapter(arrayAdapter);

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.transactions_list, menu);
        return true;
    }

}
