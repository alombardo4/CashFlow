package com.mikesandfriends.cashflow.activities;

import com.mikesandfriends.cashflow.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
/**
 * This is the Welcome screen that is displayed as soon as the
 * user opens the app.
 * @author Alec Lombardo
 * @version 1.0
 */
public class WelcomeActivity extends Activity {


    @Override
    /**
     * Called when the view is created
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getActionBar().setTitle("Welcome to CashFlow");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));

        Button loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the user clicks on the button
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

}
