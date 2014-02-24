package com.mikesandfriends.cashflow;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_account);
		
Button submitNewAccount = (Button) findViewById(R.id.addthisaccount);

        submitNewAccount.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Called when the user clicks on the button
        	 */
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), AccountActivity.class);
				
				EditText accountName = (EditText)findViewById(R.id.accountname);
		        User user = (User)getIntent().getExtras().getSerializable("user");
		        UserDataHandler dh = new UserDataHandler(getBaseContext());
		        ArrayList<Account> accountList = dh.getAccountsForUser(user);
		       
		        if(!accountList.contains(accountName.getText())){
		        	dh.createAccount(accountName.getText().toString(), user);
		        	startActivity(i);
		        	dh.closeAdapt();
		        	finish();
		        }
		        else{
		        	CharSequence text = "Account already exists!";
		        	int duration = Toast.LENGTH_SHORT;
		        	accountName.setText("");
		        	Toast.makeText(getBaseContext(), text, duration).show();
		        	
		        }
			}
		});
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_account, menu);
		return true;
	}

}
