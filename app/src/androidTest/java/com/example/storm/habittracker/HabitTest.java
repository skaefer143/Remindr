package com.example.storm.habittracker;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Storm on 2016-10-02.
 */
@RunWith(AndroidJUnit4.class)
public class HabitTest {

    @Test
    public void getHabitNameTest(){
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);

        String name = "Test1";

        assertTrue(name.equals(habit1.getHabitName()));
    }
    @Test
    public void getPastCompletionsTest(){
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        habit1.setCompletedToday(true);

        assertTrue(habit1.getPastCompletions().size() == 1);
    }
    @Test
    public void isCompletedTodayTest(){
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        Habit habit2 = new Habit("Test2", "Today2", daysOfWeek);
        habit1.setCompletedToday(true);

        assertTrue(habit1.isCompletedToday());
        assertFalse(habit2.isCompletedToday());

    }
    @Test
    public void getCompletionsTest(){
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        habit1.setCompletedToday(true);

        assertTrue(habit1.getCompletions() == 1);
    }
    @Test
    public void needToCompleteTodayTest(){
        boolean[] daysOfWeek = new boolean[7]; //all false
        boolean[] daysOfWeekAllDays = new boolean[7];
        //set all days to true, so needToBeCompleteToday will always return true
        for (int i = 0; i < 7; i++){
            daysOfWeekAllDays[i] = true;
        }
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        Habit habit2 = new Habit("Test2", "Today2", daysOfWeekAllDays);

        assertFalse(habit1.needToCompleteToday());
        assertTrue(habit2.needToCompleteToday());

    }
    @Test
    public void setCompletedTodayTest(){
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        habit1.setCompletedToday(true);

        assertTrue(habit1.isCompletedToday());
        assertTrue(habit1.getCompletions() == 1);
        assertTrue(habit1.getPastCompletions().size() == 1);

    }


}
