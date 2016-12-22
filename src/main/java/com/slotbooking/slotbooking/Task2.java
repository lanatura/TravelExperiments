package com.slotbooking.slotbooking;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.WebDriver;

import com.twilio.sdk.TwilioRestException;
public class Task2 {
	
  public static void main(String[] args) {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        // task to run goes here
    	  Example ex = new Example(null);
    	  try {
			ex.execute();
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Hello !!!");
      }
    };
    Timer timer = new Timer();
    long delay = 0;
    long intevalPeriod = 1 * 1000 * 300 ; 
    // schedules the task to be run in an interval 
    timer.scheduleAtFixedRate(task, delay,
                                intevalPeriod);
  } // end of main
}