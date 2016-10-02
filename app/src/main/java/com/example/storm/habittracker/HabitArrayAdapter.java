package com.example.storm.habittracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Storm on 2016-10-01.
 */

public class HabitArrayAdapter extends ArrayAdapter<Habit> {
    //got the idea and help from https://www.sitepoint.com/custom-data-layouts-with-your-own-android-arrayadapter/

    private Context context;
    private ArrayList<Habit> habitList;
    private HabitListController habitListController;


    //constructor
    public HabitArrayAdapter(Context context, HabitListController habitListController) {
        super(context, 0, habitListController.getHabitList().getArrayList());
        //0 because our custom getView already handles our xml layout file

        this.context = context;
        this.habitList = habitListController.getHabitList().getArrayList();
        this.habitListController = habitListController;
    }

    //when making the list, this is called
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the habit we are displaying
        Habit habit = habitList.get(position);

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
}


