/* HabitCompletion:

Purpose: This is an object that represent any habit completion instance. This way, all the Date and
completionNumber is stored in it's own object.

Design Rationale: I did this because this makes more sense from an OO perspective. Now we have
individual instances we can delete, instead of just strings in a list.

Outstanding Issues: I'm Overriding the normal ArrayList<HabitCompletion>.toString() method with my
own, that will display my own values. There might be better ways of displaying the data in a
ListView, but we used this in lonelyTwitter, so I assumed this would work fine.

Copyright {2016} {Nathan Storm Kaefer} */


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
