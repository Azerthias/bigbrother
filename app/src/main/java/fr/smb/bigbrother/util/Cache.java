package fr.smb.bigbrother.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Cache {
    static SharedPreferences preferences;
    static AppCompatActivity app;

    static int card;

    public static void init(AppCompatActivity a){
        app = a;
        preferences = app.getSharedPreferences("prefData", Context.MODE_PRIVATE);
    }

    public static void update(){
        if(preferences == null){
            preferences = app.getSharedPreferences("prefData", Context.MODE_PRIVATE);
        }
    }

    public static String getName(){
        update();
        return preferences.getString("name",null);
    }

    public static int getCard(){
        if(preferences != null){
            card =preferences.getInt("tag", 0);
        }
        return card;

    }

    public static boolean isLogged(){
        update();
        return preferences.getBoolean("logged",false);
    }
}
