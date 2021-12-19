package fr.smb.bigbrother.util.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Database {
    public static FirebaseDatabase database;
    public static void init(){
        database = FirebaseDatabase.getInstance();
    }

    public static void update(){
        if(database == null){
            database = FirebaseDatabase.getInstance();
        }
    }

    public static void write(String ref,String[] childs, Object data){
        update();

        DatabaseReference dr = database.getReference(ref);
        for(String s : childs){
            dr = dr.child(s);
        }
        dr.setValue(data);

    }

    public static DatabaseReference getReference(String path){
        String[] s = path.split("/");
        DatabaseReference dr = database.getReference(s[0]);
        for(int i = 1; i < s.length; i++){
            dr = dr.child(s[i]);
        }
        return dr;
    }

    public static DatabaseReference getReference(String ref, String[] childs){
        DatabaseReference dr = database.getReference(ref);
        for(String s : childs){
            dr = dr.child(s);
        }
        return dr;
    }

    public static DatabaseReference getReference(String[] dir){
        DatabaseReference dr = database.getReference(dir[0]);
        for(int i = 1; i < dir.length; i++){
            dr = dr.child(dir[i]);
        }
        return dr;
    }

    public static void write(String path, Object data){
        update();


        String[] s = path.split("/");
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put(s[s.length-1], data);
        getReference(path).getParent().updateChildren(updates);

    }

    public static void remove(String path){
        getReference(path).removeValue();
    }



    static String s = null;

    public static FirebaseDatabase getDatabase(){
        update();
        return database;
    }
}
