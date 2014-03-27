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

public class LoginActivity extends Activity {

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActionBar().setTitle("Log In");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));

        Button loginButton = (Button) findViewById(R.id.loginscreenloginbutton);

        final UserDataHandler lh = new UserDataHandler(getBaseContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(final View v) {
                Intent i = new Intent(getBaseContext(), AccountActivity.class);
                EditText username = (EditText) findViewById(R.id.usernametext);
                EditText password = (EditText) findViewById(R.id.passwordtext);

                User user = new User(username.getText().toString(),
                    password.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                i.putExtras(bundle);
                if (lh.checkLogin(user)) {
                    startActivity(i);
                    lh.closeAdapt();
                    finish();
                } else {
                    CharSequence text = "Incorrect Username or Password";
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
        getActionBar().setTitle("Login");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

}
