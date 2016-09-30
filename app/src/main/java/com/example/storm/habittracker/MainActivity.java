package com.example.storm.habittracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    private ListView habitListView;
    private ArrayAdapter<Habit> adapter;
    private HabitListController habitListController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        habitListController = new HabitListController(getApplicationContext());


        //Sets adapter
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitListController.getHabitList().getArrayList());
        habitListView = (ListView) findViewById(R.id.habitListView);
        habitListView.setAdapter(adapter);
        habitListController.setAdapter(adapter); //so we can notify of changes, which makes the list update

        //add a habit button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addHabitFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call activity launcher, launches to Habit creator activity

                Intent addHabitIntent = new Intent(MainActivity.this, addHabitActivity.class);
                startActivity(addHabitIntent);
            }
        });


        //Clicking on a habitList element should launch to HabitViewerActivity
        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

            //delete the habit
        habitListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
            int position, long id) {
                AlertDialog.Builder ADB = new AlertDialog.Builder(MainActivity.this);
                final ArrayList<Habit> list = habitListController.getHabitList().getArrayList();
                ADB.setMessage("Delete " + list.get(position).toString() + "?");
                ADB.setCancelable(true);
                final int finalPosition = position;
                ADB.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = list.get(finalPosition);
                        habitListController.removeHabit(habit);
                    }
                });
                ADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                ADB.show();
                return false;
            }
        });
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
