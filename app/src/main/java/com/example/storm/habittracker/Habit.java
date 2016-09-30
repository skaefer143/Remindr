package com.example.storm.habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Storm on 2016-09-27.
 */

public class Habit {
    //initialize all Habit data
    private int lengthOfWeek = 7;
    private Date habitDate = null;
    private String habitName = null;
    private boolean[] daysOfWeek = new boolean[lengthOfWeek];
    private int completions = 0;
    private int perDayCompletions = 0;
    private boolean completedToday = false;
    private ArrayList<HabitCompletion> pastCompletions = new ArrayList<HabitCompletion>();



    Habit (){

    }

}
