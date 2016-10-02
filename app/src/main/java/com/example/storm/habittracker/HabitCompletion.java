package com.example.storm.habittracker;

import java.util.Date;

/**
 * Created by Storm on 2016-09-29.
 */
public class HabitCompletion {
    private Date completedDate = null;
    private int completedNumber = 0;

    HabitCompletion(Integer completedNumber){
        this.completedDate = new Date();
        this.completedNumber = completedNumber;
    }

    //overrides the basic toString function, so HabitCompletion looks correct in listview
    @Override
    public String toString(){
        return "Completion #" + String.valueOf(completedNumber) + ": on " + completedDate.toString();
    }

    public int getCompletedNumber(){
        return this.completedNumber;
    }
}
