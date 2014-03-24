package com.mikesandfriends.cashflow.activities;

import java.util.GregorianCalendar;

import com.mikesandfriends.cashflow.Account;
import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.SpendingCategory;
import com.mikesandfriends.cashflow.Transaction;
import com.mikesandfriends.cashflow.User;
import com.mikesandfriends.cashflow.database.UserDataHandler;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MyAccountActivity extends Activity {
	
	private String ids;
	private User user;
	private UserDataHandler udl;
	private TextView balanceOnScreen;
	private DatePicker date;
	private Spinner categorySpinner;
	private final String[] CATEGORIES = {"Food", "Rent", "Clothing",
			"Entertainment"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_account);
		
		ids = (String) getIntent().getSerializableExtra("accountName");

		
		
		
		getActionBar().setTitle(ids);
        getActionBar().setBackgroundDrawable(new ColorDrawable(
        		Color.parseColor("#035986")));
        
        Button addButton = (Button) findViewById(R.id.addButton);
        Button minusButton = (Button) findViewById(R.id.minusButton);
        date = (DatePicker) findViewById(R.id.transactiondate);
        categorySpinner = (Spinner) findViewById(R.id.categoryspinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, CATEGORIES);
        categorySpinner.setAdapter(spinnerAdapter);
        
        
        udl = new UserDataHandler(getBaseContext());
        user = (User)getIntent().getSerializableExtra("user");
        
        //Display the Balance on Create
        int balance = udl.getBalanceForAccount(new Account(ids), user);
		balanceOnScreen = (TextView) findViewById(R.id.accountBalance);
		balanceOnScreen.setText("Balance: $" + balance);
        
        
        addButton.setOnClickListener(new View.OnClickListener() {
        	@Override
    		public void onClick(View v) {
    			int balance = udl.getBalanceForAccount(new Account(ids), user);
    			EditText transAmount = (EditText)
    					findViewById(R.id.transactionAmount);
    			EditText description = (EditText)
    					findViewById(R.id.description);
    			if(description.getText().toString().length() >= 1 &&
    					transAmount.getText().toString().length() >= 1){
	    			int money =
	    					Integer.parseInt(transAmount.getText().toString());
	    			int afterTrans = balance + money;
	    			balanceOnScreen.setText("Balance: $" + afterTrans);
	    			GregorianCalendar day = new GregorianCalendar();
	    			day.set(GregorianCalendar.DAY_OF_MONTH,
	    					date.getDayOfMonth());
	    			day.set(GregorianCalendar.MONTH, date.getMonth());
	    			day.set(GregorianCalendar.YEAR, date.getYear());
	    			SpendingCategory cat = SpendingCategory.FOOD;
	    			if (categorySpinner.getSelectedItemPosition() == 0) {
	    				cat = SpendingCategory.FOOD;
	    			} else if (categorySpinner.getSelectedItemPosition() == 1) {
	    				cat = SpendingCategory.RENT;
	    			} else if (categorySpinner.getSelectedItemPosition() == 2) {
	    				cat = SpendingCategory.CLOTHING;
	    			} else if (categorySpinner.getSelectedItemPosition() == 3) {
	    				cat = SpendingCategory.ENTERTAINMENT;
	    			}
	    			final Transaction trans = new Transaction(description.toString(),
	    					money, cat, day);
	    			udl.addTransactiontoAccount(trans, new Account(ids), user);
    			}
    			else{
    				CharSequence text;
    				if(description.getText().toString().length() < 1){
    					text = "Description must be at least 1 character in length";
    				}
    				else{
    					text = "Amount must be at least 1 character in length";
    				}
			       	int duration = Toast.LENGTH_SHORT;
		        	description.setText("");
		        	transAmount.setText("");
					Toast.makeText(getBaseContext(), text, duration).show();
    			}
        	}
    			
        });
        

        minusButton.setOnClickListener(new View.OnClickListener() {
        	@Override
    		public void onClick(View v) {
    			int balance = udl.getBalanceForAccount(new Account(ids), user);
    			EditText transAmount = (EditText) findViewById(R.id.transactionAmount);
    			EditText description = (EditText) findViewById(R.id.description);
    			if(description.getText().toString().length() >= 1 && transAmount.getText().toString().length() >= 1){
	
	    			int money = Integer.parseInt(transAmount.getText().toString());
	    			int afterTrans = balance - money;
	    			balanceOnScreen.setText("Balance: $" + afterTrans);
	    			money = money - 2*money;
	    			GregorianCalendar day = new GregorianCalendar();
	    			day.set(GregorianCalendar.DAY_OF_MONTH,
	    					date.getDayOfMonth());
	    			day.set(GregorianCalendar.MONTH, date.getMonth());
	    			day.set(GregorianCalendar.YEAR, date.getYear());
	    			SpendingCategory cat = SpendingCategory.INCOME;
	    			Transaction trans = new Transaction(description.toString(),
	    					money, cat, day);
	    			udl.addTransactiontoAccount(trans, new Account(ids), user);
    			}
    			else{
    				CharSequence text;
    				if(description.getText().toString().length() < 1){
    					text = "Description must be at least 1 character in length";
    				}
    				else{
    					text = "Amount must be at least 1 character in length";
    				}
			       	int duration = Toast.LENGTH_SHORT;
		        	description.setText("");
		        	transAmount.setText("");
					Toast.makeText(getBaseContext(), text, duration).show();
    			}
        	}
        });
        
        
        
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account, menu);
		return true;
	}
	
	

}
