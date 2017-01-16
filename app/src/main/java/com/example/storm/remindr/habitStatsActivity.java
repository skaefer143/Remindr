/* habitStatsActivity:

Purpose: This is the activity that runs when a habit is clicked on in MainActivity. It displays the
stats of the habit, such as whether it's been completed today and also the past completions. It also
contains the completion button, that marks the Habit as completed for today.

Design Rationale: I made the set...() methods to simplify reading how all the different TextViews are
set. Also, they could potentially be called 2 or more times (for instance when the habit completion
button is pressed) so that's why I did that.

Outstanding Issues:

Copyright 2016 Nathan Storm Kaefer */

package com.example.storm.remindr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class habitStatsActivity extends AppCompatActivity implements Serializable{

    private HabitListController habitListController;
    private ListView pastCompletionsListView;
    private int position;
    private ArrayAdapter<HabitCompletion> adapter;
    private Habit currentHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_stats);

        //setup objects that need to be created
        pastCompletionsListView = (ListView) findViewById(R.id.pastCompletions);
        habitListController = new HabitListController(getApplicationContext());


        //load habitList from file, using habitListController
        //allows for ease of transferring the habitList between activities
        habitListController.loadFromFile();
        this.position = (Integer)getIntent().getSerializableExtra("position");
        currentHabit = habitListController.getHabitList().getArrayList().get(position);

        //set all the textViews
        setHabitName();
        setDisplayCompletionsToday();
        setCompleteTodayNeed();
        setCompletionAmount();

        //check the complete button
        Button completionButton = (Button) findViewById(R.id.completionButton);
        completionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set that we completed it
                currentHabit.setCompletedToday(true);

                //update everything
                setDisplayCompletionsToday();
                setCompleteTodayNeed();
                setCompletionAmount();


                //update pastCompletionsListView
                adapter.notifyDataSetChanged();

                //save in file
                habitListController.saveInFile();

            }
        });

        //delete the habit
        pastCompletionsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                AlertDialog.Builder ADB = new AlertDialog.Builder(habitStatsActivity.this);
                final ArrayList<HabitCompletion> list = currentHabit.getPastCompletions();
                ADB.setMessage("Delete Completion #" +
                        String.valueOf(list.get(position).getCompletedNumber()) + "?");
                ADB.setCancelable(true);
                final int finalPosition = position;
                ADB.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(finalPosition);
                        adapter.notifyDataSetChanged();
                    }
                });
                ADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                ADB.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        //set the adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                currentHabit.getPastCompletions());
        pastCompletionsListView.setAdapter(adapter);
    }


    private void setHabitName() {
        TextView displayHabitName = (TextView)findViewById(R.id.displayHabitName);
        String habitName = currentHabit.getHabitName();
        displayHabitName.setText(habitName);

    }

    private void setDisplayCompletionsToday() {
        TextView displayCompletedToday = (TextView) findViewById(R.id.displayCompletedToday);

        String isCompletedToday;
        if ( currentHabit.isCompletedToday()) {
            isCompletedToday = "Yes";
        } else {
            isCompletedToday = "No";
        }
        displayCompletedToday.setText("Completed Today: " + isCompletedToday);
    }

    private void setCompleteTodayNeed() {
        TextView displayCompleteTodayNeed = (TextView) findViewById(R.id.displayCompleteTodayNeed);

        String needToComplete;
        if ( currentHabit.needToCompleteToday()) {
            needToComplete = "Yes";
        } else {
            needToComplete = "No";
        }
        displayCompleteTodayNeed.setText("Needs to be Completed Today: " + needToComplete);
    }

    private void setCompletionAmount() {
        TextView displayCompletionAmount = (TextView) findViewById(R.id.displayCompletionAmount);
        int completionAmount = currentHabit.getCompletions();
        displayCompletionAmount.setText("Has been completed " + String.valueOf(completionAmount) + " times");
    }



}
