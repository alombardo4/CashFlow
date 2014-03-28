package com.mikesandfriends.cashflow.activities;

import com.mikesandfriends.cashflow.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

/**
 * Activity to show whether or not the login worked
 * successfully.
 *
 * @author Chelsea
 *
 */

public class M4SuccessActivity extends Activity {

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m4_success);

        getActionBar().setTitle("You logged in!");
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#035986")));

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m4_success, menu);
        return true;
    }

}
