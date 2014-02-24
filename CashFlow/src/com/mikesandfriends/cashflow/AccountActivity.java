package com.mikesandfriends.cashflow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		Button newAccount = (Button) findViewById(R.id.newaccount);
		
		newAccount.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Called when the user clicks on the button
        	 */
			@Override
			public void onClick(View v) {
				User user = (User)getIntent().getExtras().getSerializable("user");
				Intent i = new Intent(getBaseContext(), NewAccountActivity.class);
				i.putExtras(getIntent().getExtras());
		        startActivity(i);
		        finish();
		        
		         
		        
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}

}
