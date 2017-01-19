/* addHabitActivity:

Purpose: This is the activity that launches when we hit the add Habit Button in Main activity.
It's purpose is to check for correct input, save everything to a Habit, and add that habit to the main habitList.

Design Rationale: This class is mostly just a big onCreate method, with lots of Button and EditText
listeners, so we can correctly save the inputted data. The two methods setEditDateText and isValidDate are
methods for simple ease of use. Really it just makes the code look neater.

Outstanding Issues: The big section of checkbox listeners is a little bit messy, however I couldn't
figure out a good way of condensing it. It was easier to implement by checking every single checkbox
in it's own setOnCheckedChangeListener.

Websites that helped with code:
Beginners Book Article
http://beginnersbook.com/2013/05/current-date-time-in-java/
    - Chaitanya Singh

Java2s
http://www.java2s.com/Tutorial/Java/0120__Development/CheckifaStringisavaliddate.htm

Copyright 2016 Nathan Storm Kaefer */

package com.example.storm.remindr;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addHabitActivity extends AppCompatActivity {

    //this date format is used to check for correct dates.
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private boolean firstClear = false;
    private boolean[] daysOfWeek = new boolean[7];
    private Date startDate;
    private Calendar reminderTime;
    //0 is monday, 1 is tuesday, so on.

    TextView timeButton = (TextView) findViewById(R.id.timeText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        //load habitList from file, using habitListController
        //allows for ease of transferring the habitList between activities
        final HabitListController habitListController = new HabitListController(getApplicationContext());
        habitListController.loadFromFile();


        //set the date in the editDateText textbox
        setEditDateText();

        final EditText editDateText = (EditText) findViewById(R.id.editDateText);
        final EditText editNameText = (EditText) findViewById(R.id.editNameText);

        //submit Habit Button code.
        Button submitHabitButton = (Button) findViewById(R.id.submitHabitButton);
        submitHabitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if date is not in correct format, don't save habit
                String formattedDateString = editDateText.getText().toString().trim();
                if(TextUtils.isEmpty(formattedDateString)){
                    editDateText.setError("The Date Field cannot be empty, and must be in format YYYY-MM-DD.");
                    setEditDateText();
                    return;
                }
                if(!(isValidDate(formattedDateString))){
                    editDateText.setError("Your date is not a valid date. Since it is a Habit start date, it " +
                            "cannot be after this date.");
                    setEditDateText();
                    return;
                }

                //if the name of the habit is empty or hasn't been changed
                String formattedNameString = editNameText.getText().toString().trim();
                if(TextUtils.isEmpty(formattedNameString)){
                    editNameText.setError("The Name Field cannot be empty.");
                    return;
                }
                if(!firstClear){
                    editNameText.setError("You must supply a name.");
                    return;
                }

                //check the time text
                String formattedTimeString = timeButton.getText().toString().trim();
                if(TextUtils.isEmpty(formattedTimeString)){
                    timeButton.setError("Please set a time to be reminded.");
                    return;
                }

                Toast.makeText(addHabitActivity.this, "Adding a Habit!", Toast.LENGTH_SHORT).show();

                //set notification event, for the future, at date and time specified
                startAlarm(timeButton.getText().toString(), daysOfWeek);

                //save habit
                Habit habit = new Habit(editNameText.getText().toString(),
                        startDate, daysOfWeek, reminderTime);
                habitListController.addHabit(habit);
                finish();
            }
        });

        //clear editNameText gray text when clicked
        editNameText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!firstClear){
                    editNameText.getText().clear();
                    firstClear = true;
                }
                editNameText.setTextColor(Color.BLACK);
            }
        });

        editDateText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDateText.setTextColor(Color.BLACK);
            }
        });

        //open time picker
        timeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // make a date fragment when clicked
                DialogFragment frag = new TimeSelector();
                frag.show(getFragmentManager(), "TimePicker");
            }
        });

        //check all checkboxes for all days of the week
        final CheckBox checkboxMonday = (CheckBox)findViewById(R.id.checkboxMonday);
        checkboxMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[0] = true;
                }
                else{
                    daysOfWeek[0] = false;
                }
            }
        });
        final CheckBox checkboxTuesday = (CheckBox)findViewById(R.id.checkboxTuesday);
        checkboxTuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[1] = true;
                }
                else{
                    daysOfWeek[1] = false;
                }
            }
        });
        final CheckBox checkboxWednesday = (CheckBox)findViewById(R.id.checkboxWednesday);
        checkboxWednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[2] = true;
                }
                else{
                    daysOfWeek[2] = false;
                }
            }
        });
        final CheckBox checkboxThursday = (CheckBox)findViewById(R.id.checkboxThursday);
        checkboxThursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[3] = true;
                }
                else{
                    daysOfWeek[3] = false;
                }
            }
        });
        final CheckBox checkboxFriday = (CheckBox)findViewById(R.id.checkboxFriday);
        checkboxFriday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[4] = true;
                }
                else{
                    daysOfWeek[4] = false;
                }
            }
        });
        final CheckBox checkboxSaturday = (CheckBox)findViewById(R.id.checkboxSaturday);
        checkboxSaturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[5] = true;
                }
                else{
                    daysOfWeek[5] = false;
                }
            }
        });
        final CheckBox checkboxSunday = (CheckBox)findViewById(R.id.checkboxSunday);
        checkboxSunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysOfWeek[6] = true;
                }
                else{
                    daysOfWeek[6] = false;
                }
            }
        });
    }




    //methods

    private void setEditDateText() {
        //sets the date for the editDateText EditText
        final EditText editDateText = (EditText) findViewById(R.id.editDateText);
        Date currentDate = new Date();
        String dateText = dateFormat.format(currentDate);
        //used http://beginnersbook.com/2013/05/current-date-time-in-java/

        editDateText.setText(dateText);
    }

    private boolean isValidDate(String dateString){
        //got idea from http://www.java2s.com/Tutorial/Java/0120__Development/CheckifaStringisavaliddate.htm

        Date inputDate;
        Date currentDate = new Date();

        dateFormat.setLenient(false);
        try {
            inputDate = dateFormat.parse(dateString.trim());
        } catch (ParseException pe) {
            return false;
        }
        if(inputDate.after(currentDate)){
            return false;
        }
        startDate = inputDate; //crucial, sets the start date to our parsed date
        //gets passed to our habit creation
        return true;
    }

    private void startAlarm(String timeText, boolean[] daysOfWeek) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //reminderTime gets set in isValidTime()
        if(!isValidTime(timeText)){
            Log.i("invalid", "Invalid Time, Notification not set.");
            return; //not a valid time
        }

        //set when we want the alarm to go off
        long when = reminderTime.getTimeInMillis();         // notification time
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(addHabitActivity.this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, when, pendingIntent);
    }

    private boolean isValidTime(String timeText){
        reminderTime = Calendar.getInstance();
        reminderTime.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
        try{
            reminderTime.setTime(timeFormat.parse(timeText));
        } catch (ParseException pe) {
            System.out.println(timeText);
            pe.printStackTrace();
            return false; //don't want to set notification if it isn't going to work anyways
        }
        return true;
    }
}
