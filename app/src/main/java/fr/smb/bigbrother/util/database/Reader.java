package fr.smb.bigbrother.util.database;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Reader {

    public Object value = null;

    public Reader(String path, TextView tv, String text){


        Database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue();
                String[] split = text.split("&data&");
                String end = split[0];
                for(int i = 1; i < split.length;i++){
                    end = end + value + split[i];
                }
                tv.setText(end);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
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

    public Reader(String path){


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
    }
}
