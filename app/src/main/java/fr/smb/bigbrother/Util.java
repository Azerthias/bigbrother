package fr.smb.bigbrother;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Util {

    public static FirebaseDatabase database;
    static SharedPreferences preferences;
    static AppCompatActivity app;

    public static String[] jours = {"lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};

    public static void init(AppCompatActivity a){
        app = a;
        database = FirebaseDatabase.getInstance();
        preferences = app.getSharedPreferences("prefData", Context.MODE_PRIVATE);
    }

    public static void update(){
        if(database == null){
            database = FirebaseDatabase.getInstance();
        }
        if(preferences == null){
            preferences = app.getSharedPreferences("prefData", Context.MODE_PRIVATE);
        }
    }

    public static void write(String ref, Object data){
        update();
        database.getReference(ref).setValue(data);
    }

    public static void write(String ref,String[] childs, Object data){
        update();

        /*DatabaseReference dr = database.getReference(ref);
        for
                setValue(data);*/
    }

    static String s = null;

    public static FirebaseDatabase getDatabase(){
        update();
        return database;
    }

    public static String getName(){
        update();
        return preferences.getString("name",null);
    }

    public static int getCard(){
        update();
        return preferences.getInt("tag",0);
    }

    public static boolean isLogged(){
        update();
        return preferences.getBoolean("logged",false);
    }

    public static void print(String tag, String msg){
        Log.println(Log.ASSERT,tag,msg);
    }

    public static void print(String msg){
        Log.println(Log.ASSERT,"tag",msg);
    }

    public static String read(String ref){





        return s;


               /* .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
                /*.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                s = dataSnapshot.getValue(String.class);
                Log.println(Log.ASSERT,"value",s);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });*/
        //return s;
    }
}
