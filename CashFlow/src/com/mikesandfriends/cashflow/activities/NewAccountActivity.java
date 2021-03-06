package com.mikesandfriends.cashflow.activities;

import java.util.ArrayList;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.User;
import com.mikesandfriends.cashflow.database.UserDataHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity for creating a new account for the user.
 *
 * @author Chelsea
 *
 */
public class NewAccountActivity extends Activity {
    /**
     * The error message if account exists.
     */
    private static final CharSequence TEXT = "Account already exists!";

    @Override
    protected final void onCreate(final Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_new_account);
        getActionBar().setTitle("New Account");
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#035986")));
        final Button submitNewAccount = (Button)
            findViewById(R.id.addthisaccount);

        submitNewAccount.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View view) {
                final Intent intent =
                        new Intent(getBaseContext(), AccountActivity.class);
                intent.putExtras(getIntent().getExtras());
                final EditText accountName =
                        (EditText) findViewById(R.id.accountname);
                final User user = (User)
                    getIntent().getExtras().getSerializable("user");
                final UserDataHandler udh =
                    new UserDataHandler(getBaseContext());
                final ArrayList<Account> accountList =
                    udh.getAccountsForUser(user);
                final Account newAccount = new Account(accountName.getText()
                        .toString());
                if (accountList.contains(newAccount)) {
                    final int duration = Toast.LENGTH_SHORT;
                    accountName.setText("");
                    Toast.makeText(getBaseContext(), TEXT, duration).show();
                } else {
                    udh.createAccount(newAccount, user);
                    startActivity(intent);
                    udh.closeAdapt();
                    finish();
                }
            }
        });

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

}
