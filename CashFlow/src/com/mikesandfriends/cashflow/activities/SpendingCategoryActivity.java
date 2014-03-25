package com.mikesandfriends.cashflow.activities;

import java.util.HashMap;
import java.util.Map;

import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.R.layout;
import com.mikesandfriends.cashflow.R.menu;
import com.mikesandfriends.cashflow.SpendingCategoryReport;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.TextView;

public class SpendingCategoryActivity extends Activity {
	private TextView food, rent, clothing, entertainment, total;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_category);
        getActionBar().setTitle("Spending Report");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
        		Color.parseColor("#035986")));
        food = (TextView) findViewById(R.id.foodcategory);
        rent = (TextView) findViewById(R.id.rentcategory);
        clothing = (TextView) findViewById(R.id.clothingcategory);
        entertainment = (TextView) findViewById(R.id.entertainmentcategory);
        total = (TextView) findViewById(R.id.totalcategory);
        
        SpendingCategoryReport report =
        		(SpendingCategoryReport)
        		getIntent().getExtras().getSerializable("report");
        Map<Integer, Integer> map = report.getSpendingReport();
        
        food.setText("Food: $" + (-1 * map.get(1)));
        rent.setText("Rent: $" + (-1 * map.get(4)));
        clothing.setText("Clothing: $" + (-1 * map.get(2)));
        entertainment.setText("Entertainment: $" + (-1 * map.get(3)));
        int tot = map.get(1) + map.get(2) + map.get(3) + map.get(4);
        tot *= -1;
        total.setText("Total: $" + tot);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending_category, menu);
		return true;
	}

}
