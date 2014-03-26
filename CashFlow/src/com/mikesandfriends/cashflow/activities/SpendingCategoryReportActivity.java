package com.mikesandfriends.cashflow.activities;

import java.util.GregorianCalendar;

import com.mikesandfriends.cashflow.Account;
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

public class SpendingCategoryReportActivity extends Activity {
    private DatePicker start, end;
    private Button generate;
    private User user;
    private Account account;
    private UserDataHandler udh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            public void onClick(View v) {
                GregorianCalendar startdate = new GregorianCalendar();
                startdate.set(GregorianCalendar.MONTH, start.getMonth());
                startdate.set(GregorianCalendar.DAY_OF_MONTH,
                        start.getDayOfMonth());
                startdate.set(GregorianCalendar.YEAR, end.getYear());
                GregorianCalendar enddate = new GregorianCalendar();
                enddate.set(GregorianCalendar.MONTH, end.getMonth());
                enddate.set(GregorianCalendar.DAY_OF_MONTH,
                        end.getDayOfMonth());
                enddate.set(GregorianCalendar.YEAR, end.getYear());
                SpendingCategoryReport report =
                        udh.generateSpendingCategoryReport(user,
                                startdate, enddate);
                if (startdate.getTimeInMillis() == enddate.getTimeInMillis()) {
                    Toast.makeText(getBaseContext(), "Enter a valid date range",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (report == null) {
                        Toast.makeText(getBaseContext(),
                                "No report could be generated",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(getBaseContext(),
                                SpendingCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("report", report);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
