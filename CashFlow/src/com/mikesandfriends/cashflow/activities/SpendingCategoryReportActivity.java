package com.mikesandfriends.cashflow.activities;

import java.util.GregorianCalendar;

import com.mikesandfriends.cashflow.R;
import com.mikesandfriends.cashflow.SpendingCategoryReport;
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
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Activity for actually generating the
 * the Spending Category Report.
 *
 * @author Chelsea
 *
 */
public class SpendingCategoryReportActivity extends Activity {
    /**
     * Object used to handle selecting dates.
     */
    private DatePicker start, end;
    /**
     * Button that the user clicks to generate the report.
     */
    private Button generate; //better kept here
    /**
     * User associated with this Spending Category Report.
     */
    private User user;

    /**
     * UserDataHandler object used to interact with the database.
     */
    private UserDataHandler udh;
    @Override
    protected final void onCreate(final Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_spending_category_report);
        getActionBar().setTitle("Spending Report");
        getActionBar().setBackgroundDrawable(new ColorDrawable(
                Color.parseColor("#035986")));
        start = (DatePicker) findViewById(R.id.startdate);
        end = (DatePicker) findViewById(R.id.enddate);
        generate = (Button) findViewById(R.id.generatespendingreport);
        user = (User) getIntent().getExtras().getSerializable("user");
        udh = new UserDataHandler(getBaseContext());

        generate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final GregorianCalendar startdate = new GregorianCalendar();
                startdate.set(GregorianCalendar.MONTH, start.getMonth());
                startdate.set(GregorianCalendar.DAY_OF_MONTH,
                        start.getDayOfMonth());
                startdate.set(GregorianCalendar.YEAR, end.getYear());
                final GregorianCalendar enddate = new GregorianCalendar();
                enddate.set(GregorianCalendar.MONTH, end.getMonth());
                enddate.set(GregorianCalendar.DAY_OF_MONTH,
                        end.getDayOfMonth());
                enddate.set(GregorianCalendar.YEAR, end.getYear());
                if (startdate.getTimeInMillis() == enddate.getTimeInMillis()) {
                    Toast.makeText(getBaseContext(), "Enter a valid date range",
                            Toast.LENGTH_SHORT).show();
                } else {
                    final SpendingCategoryReport report =
                            udh.generateSpendingCategoryReport(user,
                                    startdate, enddate);
                    if (report == null) {
                        Toast.makeText(getBaseContext(),
                                "No report could be generated",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        final Intent intent = new Intent(getBaseContext(),
                                SpendingCategoryActivity.class);
                        final Bundle bundle = new Bundle();
                        bundle.putSerializable("report", report);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }


            }
        });

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        return true;
    }

}
