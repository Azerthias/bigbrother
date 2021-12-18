package fr.smb.bigbrother.util;

import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import fr.smb.bigbrother.util.database.Database;

public class Util {

    public static String[] jours = {"lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};

    public static void init(AppCompatActivity a){
        Database.init();
        Cache.init(a);
    }

    public static void print(String tag, String msg){
        Log.println(Log.ASSERT,tag,msg);
    }

    public static void print(String msg){
        Log.println(Log.ASSERT,"tag",msg);
    }

    public static void setTitle(AppCompatActivity a, String title){
        ActionBar actionBar = a.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public static int getWeekNumber(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getDayNumber(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK)-2;
    }

    public static String getDay(){

        return jours[getDayNumber()];
    }

    public static String dayPath(int day, int h){
        return "foyer/midi/semaines/semaine" + Util.getWeekNumber() + "/" + Util.jours[day] + "/" + h + "h";
    }


}
