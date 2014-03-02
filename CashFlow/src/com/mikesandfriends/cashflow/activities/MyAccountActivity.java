package com.mikesandfriends.cashflow.activities;

import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.R.layout;
import com.mikesandfriends.cashflow.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

public class MyAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_account);
		
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		String ids = b.getString("accountName");
		
		getActionBar().setTitle(ids);
        getActionBar().setBackgroundDrawable(new ColorDrawable(
        		Color.parseColor("#035986")));
        
        
        
        
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account, menu);
		return true;
	}
	
	

}
