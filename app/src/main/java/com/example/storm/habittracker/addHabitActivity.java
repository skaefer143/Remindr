package com.example.storm.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class addHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        Button submitHabitButton = (Button) findViewById(R.id.submitHabitButton);
        EditText editNameText = (EditText) findViewById(R.id.editNameText);
        EditText editDateText = (EditText) findViewById(R.id.editDateText);




    }





}
