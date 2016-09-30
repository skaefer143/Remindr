package com.example.storm.habittracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView habitListView;
    private ArrayAdapter<Habit> adapter;
    private HabitListController habitListController = new HabitListController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        habitListView = (ListView) findViewById(R.id.habitListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addHabitFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call activity launcher, launches to Habit creator activity

                Intent addHabitIntent = new Intent(MainActivity.this, addHabitActivity.class);
                startActivity(addHabitIntent);
            }
        });
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //Sets adapter
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitListController.getHabitList().getArrayList());
        habitListView.setAdapter(adapter);
        habitListController.setAdapter(adapter);
    }

    /* Clicking on a habitList element should launch to HabitViewerActivity
    habitListView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
            Toast.makeText(getApplicationContext(),
                    "Click ListItem Number " + position, Toast.LENGTH_LONG)
                    .show();
        }
    });*/


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
