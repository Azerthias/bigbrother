package fr.smb.bigbrother.util.database.read;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.smb.bigbrother.util.database.Database;
import fr.smb.bigbrother.util.database.read.type.contain;
import fr.smb.bigbrother.util.database.read.type.count;
import fr.smb.bigbrother.util.database.read.type.value;

public class Reader {

    private DatabaseEvent dataEvent;

    private final out out;

    public Reader(){
        out = new out();

    }

    public void addCount(String path, String key){
        new count(path, key, out , this);
    }

    public void addValue(String path, String key){
        new value(path, key, out , this);
    }

    public void addText(String path, String key){
        new value(path, key, out , this);
    }

    public void addTest(String path, String key, String value){
        new contain(path, key, value, out , this);
    }

    public void setEvent(DatabaseEvent de){
        dataEvent = de;
        update();
    }

    public void update(){
        dataEvent.event(out);
    }

    public static void readOnTv(String path, TextView tv, String text){


        Database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] split = text.split("&data&");
                String end = split[0];
                for(int i = 1; i < split.length;i++){
                    end = end + dataSnapshot.getValue() + split[i];
                }
                tv.setText(end);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }

    /*public Reader(String path){


        Database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }*/




}
