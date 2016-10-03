/* Habit:

Purpose: This is the class for the Habit object. It outlines all the variables a Habit object needs
and builds a Habit object by passing in the Name, creation date, and the days of week we want to
complete the habit.

Design Rationale: You create a Habit by calling the constructor, which sets the basic variables which
make a habit a habit. These cannot be changed. All the other variables are get and set by methods
in the class. Also, combining setting all the completion values in the setCompleted today method
was done just to save code and time. It really just made sense. Putting the logic for getting
completedToday and needToCompleteToday in the Habit class also made sense, as this logic should be
in the Habit class, as per OO design principles.

Outstanding Issues: There was an issue with checking and comparing Calendar dates, and ideally I really
should find a better way of doing things (perhaps using a different date object altogether), but for
now it works and I've checked it works so I'm not too worried.

Websites that helped with code:
Stack Overflow Answer
http://stackoverflow.com/questions/2517709/comparing-two-java-util-dates-to-see-if-they-are-in-the-same-day
    - Michael Borgwardt

Stack Overflow Answer
http://stackoverflow.com/questions/12970296/comparing-two-calendar-objects
    - Drogba

Copyright 2016 Nathan Storm Kaefer */

package com.example.storm.habittracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Storm on 2016-09-27.
 */

public class Habit {

    //initialize all Habit data
    private Date habitDate = null;
    private Date lastCompletedDate = null;
    private String habitName = null;
    private boolean[] daysOfWeek = new boolean[7];
    private int completions = 0;
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
            /*this exception should never happen, and if it does it's because we didn't
            scrub data hard enough in addHabitActivity*/
        }
    }

    //getters
    public String getHabitName() {return habitName;}
    public ArrayList<HabitCompletion> getPastCompletions() {return pastCompletions;}
    public boolean isCompletedToday() {
        //used idea from http://stackoverflow.com/questions/2517709/comparing-two-java-util-dates-to-see-if-they-are-in-the-same-day
        Calendar pastCalDate = Calendar.getInstance();
        Calendar todayCalDate = Calendar.getInstance();
        try{
            pastCalDate.setTime(lastCompletedDate);
        } catch (NullPointerException e) {
            //if we get an exception, just set to set lastCompletedDate to default date, for comparisons
            this.lastCompletedDate = new Date(0);
            completedToday = false;
            return completedToday;
        }
        todayCalDate.setTime(new Date());
            if((pastCalDate.compareTo(todayCalDate) < 0) &&
                    (pastCalDate.get(Calendar.DAY_OF_YEAR) != todayCalDate.get(Calendar.DAY_OF_YEAR))){
                //if one day is less than the other day
                //help from http://stackoverflow.com/questions/12970296/comparing-two-calendar-objects
                completedToday = false;
            }
            else{
                completedToday = true;
            }
        return completedToday;
    }
    public int getCompletions() {return completions;}
    public boolean needToCompleteToday(){
        if(this.completedToday){
            //we don't need to complete again today, if we have completed the habit!
            return false;
        }
        //else, check today, and check which days we wanted to do this habit
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        //check all the days, and see if today is a day we need to do our habit
        if((dayOfWeek == Calendar.MONDAY) && (daysOfWeek[0])){
            return true;
        }
        if((dayOfWeek == Calendar.TUESDAY) && (daysOfWeek[1])){
            return true;
        }
        if((dayOfWeek == Calendar.WEDNESDAY) && (daysOfWeek[2])){
            return true;
        }
        if((dayOfWeek == Calendar.THURSDAY) && (daysOfWeek[3])){
            return true;
        }
        if((dayOfWeek == Calendar.FRIDAY) && (daysOfWeek[4])){
            return true;
        }
        if((dayOfWeek == Calendar.SATURDAY) && (daysOfWeek[5])){
            return true;
        }
        if((dayOfWeek == Calendar.SUNDAY) && (daysOfWeek[6])){
            return true;
        }
        //else, if none of those work
        return false;
    }


    //setters
    public void setCompletedToday(boolean completedToday) {
        this.lastCompletedDate = new Date();
        this.completedToday = completedToday;
        this.completions++;
        //add to an arraylist of pastcompletions
        HabitCompletion habitCompletion = new HabitCompletion(completions);
        this.pastCompletions.add(0, habitCompletion);
    }

}
