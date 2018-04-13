/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers.acct;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kelvin
 */
public class WeekScheduler implements Scheduler {
    public Calendar todaysDate; 
    private final int dayOfWeek;
    private Calendar systemDate = Calendar.getInstance();

    public WeekScheduler(int dayOfWeek, Date date) {
        this.dayOfWeek = dayOfWeek;
        
        systemDate.setTime(date);
        systemDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    }

    @Override
    public Date next() {
        systemDate.add(Calendar.DATE, 1);
        return systemDate.getTime();
    }

    @Override
    public Date checkNextDay() {
        if (systemDate.getTime().compareTo(todaysDate.getTime()) > 0)
            System.out.println("Yes");
        return null;
    }
    
    
    
}
