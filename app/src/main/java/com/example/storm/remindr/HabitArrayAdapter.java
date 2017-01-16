/* HabitArrayAdapter:

Purpose: Extends a normal ArrayAdapter<Habit>, however adds my own custom code for customizing the
view, updating the view when removing an item from the base Array, and logic for checking whether
Yes or No needs to be put next to the TextViews in the Array. Also uses my custom layout for viewing
Arrays of Habits.

Design Rationale: I wanted to pass in my habitListController to the Constructor, as then I can save my
instance of habitListController (and habitList) without having to load from file. Also, I made my own
remove method for removing Habits from the habitList, as I wanted more control over removing Habits,
and also this way I didn't have to make a new HabitArrayAdapter (in MainActivity) everytime
my dataset changed.

Outstanding Issues: I have Strings that I'm just writing out that aren't from a string file.

Websites that helped with code:
Sitepoint
https://www.sitepoint.com/custom-data-layouts-with-your-own-android-arrayadapter/
    -Simon Codrington

Copyright 2016 Nathan Storm Kaefer */

package com.example.storm.remindr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Storm on 2016-10-01.
 */

public class HabitArrayAdapter extends ArrayAdapter<Habit> {
    //got the idea and help from https://www.sitepoint.com/custom-data-layouts-with-your-own-android-arrayadapter/

    private HabitListController habitListController;


    //constructor
    public HabitArrayAdapter(Context context, HabitListController habitListController) {
        super(context, 0, habitListController.getHabitList().getArrayList());
        //0 because our custom getView already handles our xml layout file

        this.habitListController = habitListController;
    }

    //when making the list, this is called
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the habit we are displaying
        Habit habit = habitListController.getHabitList().getArrayList().get(position);

        //get the inflater and inflate the view
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        //get data population elements
        TextView habitName = (TextView) convertView.findViewById(R.id.habitName);
        TextView completedToday = (TextView) convertView.findViewById(R.id.completedToday);
        TextView needsToBeCompleted = (TextView) convertView.findViewById(R.id.needsToBeCompleted);

        //set the view using data
        habitName.setText(habit.getHabitName());
        //logic for checking whether complete today or not
        String isCompletedToday;
        if (habit.isCompletedToday()) {
            isCompletedToday = "Yes";
        } else {
            isCompletedToday = "No";
        }
        completedToday.setText("Habit Completed Today: " + isCompletedToday);

        //logic for checking whether needs to be complete today or not
        String needToComplete;
        if ( habit.needToCompleteToday()) {
            needToComplete = "Yes";
        } else {
            needToComplete = "No";
        }
        needsToBeCompleted.setText("Needs to be Completed Today: " + needToComplete);


        //return the completed view to render on screen
        return convertView;
    }


    public void myRemove(int position){
        habitListController.getHabitList().getArrayList().remove(position);
        HabitArrayAdapter.super.notifyDataSetChanged();
    }

}


