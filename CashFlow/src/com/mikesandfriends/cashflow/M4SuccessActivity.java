package com.mikesandfriends.cashflow;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class M4SuccessActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m4_success);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.m4_success, menu);
		return true;
	}

}
