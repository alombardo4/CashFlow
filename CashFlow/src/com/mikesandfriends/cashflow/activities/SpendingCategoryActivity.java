package com.mikesandfriends.cashflow.activities;

import java.util.Map;

import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.SpendingCategoryReport;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.TextView;

/**
 *Activity that handles displaying the
 *spending categories.
 *
 * @author Chelsea
 *
 */
public class SpendingCategoryActivity extends Activity {
    /**
     * Objects that display the spending for the categories
     * food, rent, clothing, entertainment and total.
     */
    private TextView food, rent, clothing, entertainment, total; //need it here
    /**
     * Index to retrieve entertainment from the map.
     */
    private static final int ENTINDEX = 3;
    /**
     * Index to retrieve rent from the map.
     */
    private static final int RENTINDEX = 4;


    @Override
    protected final void onCreate(final Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_spending_category);
        getActionBar().setTitle("Spending Report");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));
        food = (TextView) findViewById(R.id.foodcategory);
        rent = (TextView) findViewById(R.id.rentcategory);
        clothing = (TextView) findViewById(R.id.clothingcategory);
        entertainment = (TextView) findViewById(R.id.entertainmentcategory);
        total = (TextView) findViewById(R.id.totalcategory);

        final SpendingCategoryReport report =
                (SpendingCategoryReport)
                getIntent().getExtras().getSerializable("report");
        final Map<Integer, Integer> map = report.getSpendingReport();

        food.setText("Food: $" + (map.get(1)));
        //4 is the index that rent is in the data structure
        //and it would be unnecessarily complex to pull it out
        rent.setText("Rent: $" + (map.get(RENTINDEX)));
        clothing.setText("Clothing: $" + (-1 * map.get(2)));
        //3 is the index that entertainment is in the data structure
        //and it would be unnecessarily complex to pull it out
        entertainment.setText("Entertainment: $"
            + (map.get(RENTINDEX)));
        //the 3 and 4 are the same as above
        int tot = map.get(1) + map.get(2) + map.get(ENTINDEX)
            + map.get(RENTINDEX);
        tot *= -1;
        total.setText("Total: $" + tot);
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spending_category, menu);
        return true;
    }

}
