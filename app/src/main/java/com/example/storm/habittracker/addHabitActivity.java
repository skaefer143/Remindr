package com.example.storm.habittracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addHabitActivity extends AppCompatActivity {

    //this date format is used to check for correct dates.
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    boolean firstClear = false;
    private boolean[] daysOfWeek = new boolean[7];
    //0 is monday, 1 is tuesday, so on.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        //load habitList from file, using habitListController
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
                if(firstClear == false){
                    editNameText.setError("You must supply a name.");
                    return;
                }

                Toast.makeText(addHabitActivity.this, "Adding a Habit!", Toast.LENGTH_SHORT).show();
                //save habit
                Habit habit = new Habit(editNameText.getText().toString(), editDateText.getText().toString(), daysOfWeek);
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
        return true;
    }



}
