package com.example.storm.habittracker;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Storm on 2016-09-29.
 */

public class HabitListController implements Serializable{
    //for saving and loading of habitList

    private static final String FILENAME = "file.sav";
    private static HabitList habitList = null;
    private  ArrayAdapter<Habit> adapter;
    private Context context;

    public HabitListController(Context context){
        this.context = context;
        //need to establish context before attempting to load file
        habitList = loadFromFile();
    }


    public HabitList getHabitList() {
        habitList = loadFromFile();
        return habitList;
    }

    public HabitList loadFromFile() {
        //load from file using GSON

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            //requires context: http://stackoverflow.com/questions/3625837/android-what-is-wrong-with-openfileoutput
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //converting ArrayList into Gson ?
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();

            habitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new HabitList();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

        return habitList;
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            //requires context: http://stackoverflow.com/questions/3625837/android-what-is-wrong-with-openfileoutput

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            //fancy file saving
            Gson gson = new Gson();
            gson.toJson(habitList, out);
            out.flush();


            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void addHabit(Habit habit) {
        getHabitList().addHabit(habit);
        adapter.notifyDataSetChanged();
    }

    public void removeHabit(Habit habit) {
        getHabitList().removeHabit(habit);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter(ArrayAdapter<Habit> adapter) {
        this.adapter = adapter;
    }
}
