/* MainActivity:

Purpose: This is the activity that launches when the app opens. It displays the listview of all
habits, and that works off of a custom ArrayAdapter. The point is to be able to easily see all your
habits that you have added. There is also an add Habit floating action button in the bottom right,
so it's easy to see where to add a habit.

Design Rationale: This is an easy way to view Habits, and whether or not they need to be completed,
so this made sense to implement.

Outstanding Issues:

Copyright 2016 Nathan Storm Kaefer */

package com.example.storm.habittracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView habitListView;
    private HabitArrayAdapter adapter;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        habitListController = new HabitListController(getApplicationContext());

        //makes sure the list view is available for all to call
        habitListView = (ListView) findViewById(R.id.habitListView);

        //add a addHabit button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addHabitFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call activity launcher, launches to Habit creator activity
                Intent addHabitIntent = new Intent(MainActivity.this, addHabitActivity.class);
                startActivity(addHabitIntent);
            }
        });

        //delete the habit
        habitListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                AlertDialog.Builder ADB = new AlertDialog.Builder(MainActivity.this);
                final ArrayList<Habit> list = habitListController.getHabitList().getArrayList();
                ADB.setMessage("Delete " + list.get(position).getHabitName() + "?");
                ADB.setCancelable(true);
                final int finalPosition = position;
                ADB.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.myRemove(finalPosition);
                        habitListController.saveInFile();
                        habitListController.loadFromFile();
                        adapter.clear();
                        adapter.addAll(habitListController.getHabitList().getArrayList());
                    }
                });
                ADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                ADB.show();
                return true;
                //return true means the ADB showed up and we can't click on anything else.
            }
        });

        //Clicking on a habitList element should launch to HabitStatsActivity
        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent habitStatsIntent = new Intent(MainActivity.this, habitStatsActivity.class);
                habitStatsIntent.putExtra("position", position);
                startActivity(habitStatsIntent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //set the adapter
        habitListController.loadFromFile();
        adapter = new HabitArrayAdapter(this, habitListController);
        habitListView.setAdapter(adapter);

    }
}