package com.mikesandfriends.cashflow;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
/**
 * This is the Welcome screen that is displayed as soon as the
 * user opens the app
 * @author Alec Lombardo
 * @verison 1.0
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
				// TODO put in the transition to login page
				
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
