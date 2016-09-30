package com.example.storm.habittracker;

import java.util.ArrayList;

/**
 * Created by Storm on 2016-09-29.
 */

public class HabitList {
    ArrayList<Habit> habitList = null;


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
