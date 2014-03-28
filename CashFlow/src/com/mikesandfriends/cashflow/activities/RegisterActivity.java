package com.mikesandfriends.cashflow.activities;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**.
 * Registers a User
 *
 * @author Brett Lyle
 * @version 1.0
 *
 */
@SuppressLint("DefaultLocale")
public class RegisterActivity extends Activity {

    /**
     * The UserDataHandler object used to interact with the database.
     */
    private UserDataHandler lh;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getActionBar().setTitle("Register");
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#035986")));

        Button registerButton =
                (Button) findViewById(R.id.registerscreenregisterbutton);

        lh = new UserDataHandler(getBaseContext());

        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                EditText username = (EditText) findViewById(R.id.usernametext);
                EditText password = (EditText) findViewById(R.id.passwordtext);

                // make sure length of username/password is at least of length 1
                if (username.length() >= 1 && password.length() >= 1) {
                    if (lh.isValidUsername(username.getText().toString()
                            .toLowerCase().trim())) {
                        User user = new User(username.getText().toString()
                                .toLowerCase().trim(), password.getText()
                                .toString());
                        lh.add(user);
                        startActivity(i);
                        lh.closeAdapt();
                        finish();
                    } else {
                        // checks to see if the user already exists
                        CharSequence text = "Username not available";
                        int duration = Toast.LENGTH_SHORT;
                        username.setText("");
                        password.setText("");
                        Toast.makeText(getBaseContext(), text, duration).show();
                    }
                } else {
                    // informs user to modify username or password
                    CharSequence text =
                            "Username and Password Must"
                    + " be at least 1 character long";
                    int duration = Toast.LENGTH_SHORT;
                    username.setText("");
                    password.setText("");
                    Toast.makeText(getBaseContext(), text, duration).show();
                }

            }
        });

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

}
