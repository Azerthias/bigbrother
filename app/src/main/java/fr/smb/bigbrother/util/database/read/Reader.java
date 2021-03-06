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
    private boolean isSync = false;
    private Synchronizer sync;

    private final out out;

    String n;

    public Reader(String name){
        out = new out();
n = name;
    }

    public void addCount(String path, String key){
        new count(path, key, out , this);
    }


    public void addIntValue(String path, String key){
        new value(path, key, out , this, value.INT);
    }

    public void addBooleanValue(String path, String key){
        new value(path, key, out , this, value.BOOLEAN);
    }

    public void addStringValue(String path, String key){
        new value(path, key, out , this, value.STRING);
    }



    public void addTest(String path, String key, String value){
        new contain(path, key, value, out , this);
    }

    public void setEvent(DatabaseEvent de){
        dataEvent = de;
        realUpdate();
    }

    public void update(){
        if(isSync){
            sync.update();

        }else{
            realUpdate();
        }

    }

    public void realUpdate(){
        try {
            dataEvent.event(out);
        }catch(Exception ignored){}
    }

    public void addSynchronizer(Synchronizer s){
        sync = s;
        isSync = true;
        s.add(this);
    }



    public static void readOnTv(String path, TextView tv, String text){


        Database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] split = text.split("&data&");
                StringBuilder end = new StringBuilder(split[0]);
                for(int i = 1; i < split.length;i++){
                    end.append(dataSnapshot.getValue()).append(split[i]);
                }
                tv.setText(end.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }


}
