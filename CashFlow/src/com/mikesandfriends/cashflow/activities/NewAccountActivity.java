package com.mikesandfriends.cashflow.activities;

import java.util.ArrayList;

import com.mikesandfriends.cashflow.Account;
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

public class NewAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_account);
        getActionBar().setTitle("New Account");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
        		Color.parseColor("#035986")));
		Button submitNewAccount = (Button) findViewById(R.id.addthisaccount);

        submitNewAccount.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Called when the user clicks on the button
        	 */
			@Override
			public void onClick(final View view) {
				Intent intent = new Intent(getBaseContext(), AccountActivity.class);
				intent.putExtras(getIntent().getExtras());
				EditText accountName = (EditText)findViewById(R.id.accountname);
		        User user = (User)getIntent().getExtras().getSerializable("user");
		        UserDataHandler dh = new UserDataHandler(getBaseContext());
		        ArrayList<Account> accountList = dh.getAccountsForUser(user);
		        final Account newAccount = new Account(accountName.getText().toString());
		        if(!accountList.contains(newAccount)){
		        	dh.createAccount(newAccount, user);
		        	startActivity(intent);
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
		return true;
	}

}
