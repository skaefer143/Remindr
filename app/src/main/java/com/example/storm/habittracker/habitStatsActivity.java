package com.example.storm.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

public class habitStatsActivity extends AppCompatActivity implements Serializable{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_stats);

        //load habitList from file, using habitListController
        //allows for ease of transferring the habitList between activities
        final HabitListController habitListController = new HabitListController(getApplicationContext());
        habitListController.loadFromFile();
        final int position = (Integer)getIntent().getSerializableExtra("position");

        //set all the textViews
        setHabitName(position, habitListController);
        setDisplayCompletionsToday(position, habitListController);
        setCompleteTodayNeed(position, habitListController);
        setCompletionAmount(position, habitListController);

        //check the complete button
        Button completionButton = (Button) findViewById(R.id.completionButton);
        completionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set that we completed it
                habitListController.getHabitList().getArrayList().get(position).setCompletedToday(true);

                //update everything
                setDisplayCompletionsToday(position, habitListController);
                setCompleteTodayNeed(position, habitListController);
                setCompletionAmount(position, habitListController);

                //save in file
                habitListController.saveInFile();

            }
        });


    }


    private void setHabitName(int position, HabitListController habitListController) {
        TextView displayHabitName = (TextView)findViewById(R.id.displayHabitName);
        String habitName = habitListController.getHabitList().getArrayList().get(position).getHabitName();
        displayHabitName.setText(habitName);

    }

    private void setDisplayCompletionsToday(int position, HabitListController habitListController) {
        TextView displayCompletedToday = (TextView) findViewById(R.id.displayCompletedToday);

        String isCompletedToday;
        if ( habitListController.getHabitList().getArrayList().get(position).isCompletedToday()) {
            isCompletedToday = "Yes";
        } else {
            isCompletedToday = "No";
        }
        displayCompletedToday.setText("Completed Today: " + isCompletedToday);
    }

    private void setCompleteTodayNeed(int position, HabitListController habitListController) {
        TextView displayCompleteTodayNeed = (TextView) findViewById(R.id.displayCompleteTodayNeed);

        String needToComplete;
        if ( habitListController.getHabitList().getArrayList().get(position).needToCompleteToday()) {
            needToComplete = "Yes";
        } else {
            needToComplete = "No";
        }
        displayCompleteTodayNeed.setText("Needs to be Completed Today: " + needToComplete);
    }

    private void setCompletionAmount(int position, HabitListController habitListController) {
        TextView displayCompletionAmount = (TextView) findViewById(R.id.displayCompletionAmount);
        int completionAmount = habitListController.getHabitList().getArrayList().get(position).getCompletions();
        displayCompletionAmount.setText("Has been completed " + String.valueOf(completionAmount) + " times");
    }



}
