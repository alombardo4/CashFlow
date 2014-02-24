package com.mikesandfriends.cashflow;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class AccountActivity extends Activity {
	private ArrayList<Account> items;
	private UserDataHandler udh;
	private User user;
	private ArrayAdapter<String> arrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
        getActionBar().setTitle("Accounts");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
        		Color.parseColor("#035986")));
		user = (User)getIntent().getExtras().getSerializable("user");
		Button newAccount = (Button) findViewById(R.id.newaccount);
		ListView accountsList = (ListView) findViewById(R.id.accountslist);
		newAccount.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Called when the user clicks on the button
        	 */
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), NewAccountActivity.class);
				i.putExtras(getIntent().getExtras());
		        startActivity(i);         
			}
		});
		udh = new UserDataHandler(getBaseContext());
		items = udh.getAccountsForUser(user);
		udh.closeAdapt();
		arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1);
		for (Account item : items) {
			arrayAdapter.add(item.getName());
		}
		accountsList.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
