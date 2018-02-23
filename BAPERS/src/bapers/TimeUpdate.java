/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bapers;

import bapers.controller.Controller;
import bapers.gui.MainFrame;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Josep
 */
public class TimeUpdate extends TimerTask {

    private final Controller controller;
    private final MainFrame mainframe;

    public TimeUpdate(Controller controller, MainFrame mainframe) {
        this.controller = controller;
        this.mainframe = mainframe;
    }

    @Override
    public void run() {
        //Get todays date
        Date date = new Date();
        DateFormat day = new SimpleDateFormat("dd");
        DateFormat today = new SimpleDateFormat("yyyy/MM/dd");
        String todayDate = today.format(date);
        String s = day.format(date);
        //If todays date is the 23rd and reminder letters have not been generated then generate reminder letters for that month
        if (s.equals("23") && !controller.reminderLettersAlreadyGenerated(todayDate)) {
            //Generate reminder letters...
            mainframe.generateReminderLettersForTheMonth();
            mainframe.deleteReminderLettersTableInformation();
            //Store in databse that reminder letters have been generated for this month
            controller.setReminderLetterDate(todayDate);
        }
    }

}
