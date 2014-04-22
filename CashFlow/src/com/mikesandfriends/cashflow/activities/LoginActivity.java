package com.mikesandfriends.cashflow.activities;

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
 * The Activity that handles letting an existing user log
 * into CashFlow or displays an error message for
 * incorrect login.
 *
 * @author Chelsea
 *
 */

public class LoginActivity extends Activity {
    /**
     * Holds the text to be shown if bad login.
     */
    private final CharSequence badinput = "Incorrect Username or Password";
    @Override
    protected final void onCreate(final Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_login);

        getActionBar().setTitle("Log In");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));

        final Button loginButton =
                (Button) findViewById(R.id.loginscreenloginbutton);
        final Button forgotPass =
                (Button) findViewById(R.id.forgotButton);
        final UserDataHandler udh = new UserDataHandler(getBaseContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent(getBaseContext(),
                        AccountActivity.class);
                final EditText username =
                    (EditText) findViewById(R.id.usernametext);
                final EditText password =
                    (EditText) findViewById(R.id.passwordtext);

                final User user = new User(username.getText().toString(),
                    password.getText().toString());
                final Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                if (udh.checkLogin(user)) {
                    startActivity(intent);
                    udh.closeAdapt();
                    finish();
                } else {
                    final int duration = Toast.LENGTH_SHORT;
                    username.setText("");
                    password.setText("");
                    Toast.makeText(getBaseContext(), badinput, duration).show();

                }
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View v1) {
                final EditText username =
                    (EditText) findViewById(R.id.usernametext);
                if (udh.isValidUsername(username.getText().toString())) {
                    User recovUser =
                          udh.getUserInfo(username.getText().toString());
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  ,
                            new String[]{recovUser.getEmail()});
                    i.putExtra(Intent.EXTRA_SUBJECT,
                            "Lost Password");
                    i.putExtra(Intent.EXTRA_TEXT   ,
                            "" + recovUser.getPassword());
                    startActivity(Intent.createChooser(i, "Send mail..."));
                        udh.closeAdapt();
                        finish();
                } else {
                    final int duration = Toast.LENGTH_SHORT;
                    username.setText("");
                    Toast.makeText(getBaseContext(), badinput, duration).show();
                }
            }
        });
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        getActionBar().setTitle("Login");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

}
