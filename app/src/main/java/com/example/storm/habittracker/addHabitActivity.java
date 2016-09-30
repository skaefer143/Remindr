package com.example.storm.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class addHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);


        EditText editNameText = (EditText) findViewById(R.id.editNameText);
        EditText editDateText = (EditText) findViewById(R.id.editDateText);
        //need to pull up HabitListController. Through that, we can add the finished habit class to the main HabitList.HabitListController


    }


    public void addHabitAction(View v){
        Button submitHabitButton = (Button) findViewById(R.id.submitHabitButton);

    }





}
