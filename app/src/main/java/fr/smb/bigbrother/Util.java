package fr.smb.bigbrother;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Util {

    static FirebaseDatabase database;

    public static void init(){
        database = FirebaseDatabase.getInstance();
    }

    public static void write(String ref, Object data){
        database.getReference(ref).setValue(data);
    }

    public static Object read(String ref){
        return database.getReference(ref);
    }
}
