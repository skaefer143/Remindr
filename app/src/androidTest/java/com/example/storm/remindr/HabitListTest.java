package com.example.storm.remindr;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Storm on 2016-10-02.
 */

@RunWith(AndroidJUnit4.class)
public class HabitListTest {

    @Test
    public void addHabitTest(){
        HabitList habitList = new HabitList();
        boolean[] daysOfWeek = new boolean[7];
        Habit habit = new Habit("Test", "Today", daysOfWeek);

        habitList.addHabit(habit);

        assertTrue(habit == habitList.getArrayList().get(0));

    }
    @Test
    public void removeHabitTest(){
        HabitList habitList = new HabitList();
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        Habit habit2 = new Habit("Test2", "Today2", daysOfWeek);

        habitList.addHabit(habit1);
        habitList.addHabit(habit2);

        assertTrue(habit1 == habitList.getArrayList().get(0));
        assertTrue(habit2 == habitList.getArrayList().get(1));

        habitList.removeHabit(habit1);

        assertTrue(habit2 == habitList.getArrayList().get(0));

    }
    @Test
    public void getArrayListTest(){
        HabitList habitList = new HabitList();
        boolean[] daysOfWeek = new boolean[7];
        Habit habit1 = new Habit("Test1", "Today1", daysOfWeek);
        Habit habit2 = new Habit("Test2", "Today2", daysOfWeek);

        habitList.addHabit(habit1);
        habitList.addHabit(habit2);

        ArrayList<Habit> habitArrayList = habitList.getArrayList();

        assertTrue(habit1 == habitArrayList.get(0));
        assertTrue(habit2 == habitArrayList.get(1));

    }

}
