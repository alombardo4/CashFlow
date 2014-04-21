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
     * Text for if username is taken.
     */
    private static final CharSequence NOTAVAIL = "Username not available";

    /**
     * Text if username or password invalid.
     */
    private static final CharSequence INVALID = "Username and Password Must"
        + " be at least 1 character long";

    /**
     * The UserDataHandler object used to interact with the database.
     */
    private UserDataHandler udh;

    @Override
    protected final void onCreate(final Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_register);

        getActionBar().setTitle("Register");
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#035986")));

        final Button registerButton =
                (Button) findViewById(R.id.registerscreenregisterbutton);

        udh = new UserDataHandler(getBaseContext());

        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View v) {
                final EditText username =
                    (EditText) findViewById(R.id.usernametext);
                final EditText password =
                    (EditText) findViewById(R.id.passwordtext);
                final EditText email =
                        (EditText) findViewById(R.id.emailtxt);

                // make sure length of username/password is at least of length 1
                if (username.length() >= 1 && password.length() >= 1 && email.length()>=1) {
                    if (udh.isValidUsername(username.getText().toString()
                            .toLowerCase().trim())) {
                        final User user = new User(username.getText().toString()
                                .toLowerCase().trim(), password.getText()
                                .toString());
                        user.setEmail(email.getText().toString().toLowerCase().trim());
                        udh.add(user);
                        final Intent intent =
                            new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        udh.closeAdapt();
                        finish();
                    } else {
                        // checks to see if the user already exists
                        final int duration = Toast.LENGTH_SHORT;
                        username.setText("");
                        password.setText("");
                        Toast.makeText(
                            getBaseContext(), NOTAVAIL, duration).show();
                    }
                } else {
                    // informs user to modify username or password
                    final int duration = Toast.LENGTH_SHORT;
                    username.setText("");
                    password.setText("");
                    Toast.makeText(getBaseContext(), INVALID, duration).show();
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
