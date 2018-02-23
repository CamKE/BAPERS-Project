/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Josep
 */
public abstract class Setup {

    Date date = new Date();
    DateFormat day = new SimpleDateFormat("dd");
    DateFormat today = new SimpleDateFormat("yyyy/MM/dd");
    String s = day.format(date);
   // String todayDate = today.format(date);
    String todayDate;
    
    public Setup(String todayDate){
        this.todayDate = todayDate;
    }
    
    public abstract void run();
    
}
