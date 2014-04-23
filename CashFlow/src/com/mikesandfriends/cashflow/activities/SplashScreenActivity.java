package com.mikesandfriends.cashflow.activities;

import com.mikesandfriends.cashflow.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity{

//timer
	    private static int SPLASH_TIME_OUT = 3000;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splash_screen);
	        Thread timer = new Thread(){
	        	public void run(){
	        		try{
	        			sleep(2500);
	        		}catch(InterruptedException e){
	        			e.printStackTrace();
	        			
	        		}finally{
	        			Intent i = new Intent("com.mikesandfriends.cashflow.CLEARSCREEN");
	        			startActivity(i);
	        			
	        		}
	        	}
	        };
	        timer.start();
	        

	    }
}

