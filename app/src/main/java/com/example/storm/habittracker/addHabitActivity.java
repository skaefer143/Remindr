package com.example.storm.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addHabitActivity extends AppCompatActivity {


    final EditText editDateText = (EditText) findViewById(R.id.editDateText);
    final EditText editNameText = (EditText) findViewById(R.id.editNameText);
    //this date format is used to check for correct dates.
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        //pass my habitListController to addHabitActivity through Serialization
        Intent addHabitIntent = getIntent();
        HabitListController habitListController = (HabitListController)addHabitIntent.getSerializableExtra("Controller");


        //set the date in the editDateText textbox
        setDate();


        //submit Habit Button code.
        Button submitHabitButton = (Button) findViewById(R.id.submitHabitButton);
        submitHabitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addHabitActivity.this, "Adding a Habit!", Toast.LENGTH_SHORT).show();

                //save habit


                //if date is not in correct format, don't save habit
                if(TextUtils.isEmpty(editDateText.getText().toString())){
                    editDateText.setError("The Date Field cannot be empty, and must be in format YYYY-MM-DD");
                    return;
                }

            }
        });

        //clear editNameText gray text when clicked
        editNameText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNameText.getText().clear();

            }
        });


        editDateText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDateText.getText().clear();

            }
        });

    }

    private void setDate() {
        //sets the date for the editDateText EditText
        Date currentDate = new Date();
        String dateText = dateFormat.format(currentDate);
        //used http://beginnersbook.com/2013/05/current-date-time-in-java/

        editDateText.setText(dateText);
    }


}
