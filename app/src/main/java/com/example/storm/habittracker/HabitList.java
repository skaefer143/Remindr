/* HabitList:

Purpose: This is the Object representing an ArrayList of Habits. It constructs and handles the
ArrayList of Habits, so habitListController doesn't have to. HabitListController just calls this
objects methods.

Design Rationale: This makes sense in an OO perspective, so I made it this way. This way, HabitList
does not handle saving or loading from file.

Outstanding Issues:

Copyright 2016 Nathan Storm Kaefer */

package com.example.storm.habittracker;

import java.util.ArrayList;

/**
 * Created by Storm on 2016-09-29.
 */

public class HabitList {
    private ArrayList<Habit> habitList = null;


    public HabitList(){
        //constructing the habit list
        habitList = new ArrayList<Habit>();
    }

    public void addHabit(Habit habit){
        habitList.add(habit);
    }

    public void removeHabit(Habit habit){
        habitList.remove(habit);
    }

    public ArrayList<Habit> getArrayList(){
        //gets actual ArrayList
        return habitList;
    }


}
