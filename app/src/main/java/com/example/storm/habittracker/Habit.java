package com.example.storm.habittracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Storm on 2016-09-27.
 */

public class Habit {
    //initialize all Habit data
    private Date habitDate = null;
    private String habitName = null;
    private boolean[] daysOfWeek = new boolean[7];
    private int completions = 0;
    private int perDayCompletions = 0;
    private boolean completedToday = false;
    private ArrayList<HabitCompletion> pastCompletions = new ArrayList<HabitCompletion>();

    Habit(String nameText, String dateText, boolean[] daysOfWeek){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        this.habitName = nameText;
        this.daysOfWeek = daysOfWeek;
        try {
            this.habitDate = dateFormat.parse(dateText);
        } catch (ParseException pe) {
            System.out.println(habitDate);
            pe.printStackTrace();
            //this exception should never happen, and if it does it's because we didn't
            //scrub data hard enough in addHabitActivity
        }

        //from Abram: You can use this date to calculate how many total habits could be completed between creation date and now.
    }

}
