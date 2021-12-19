package fr.smb.bigbrother.util.database.read.type;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;
import fr.smb.bigbrother.util.database.read.Reader;
import fr.smb.bigbrother.util.database.read.out;

public class value{

    public static final int INT = 0;
    public static final int BOOLEAN = 1;
    public static final int STRING = 2;


    public value(String path, String key, out out, Reader r, int type){
        switch (type){
            case INT:
                out.addInt(key,0);
                break;
            case BOOLEAN:
                out.addBoolean(key,false);
                break;
            case STRING:
                out.addString(key,null);
                break;
        }

        Database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    switch (type){
                        case INT:
                            out.updateInt(key, dataSnapshot.getValue(int.class));
                            break;
                        case BOOLEAN:
                            if(dataSnapshot.getValue().toString().equals("true")){
                                out.updateBoolean(key, true);
                            }else{
                                out.updateBoolean(key, false);
                            }

                            break;
                        case STRING:

                            out.updateString(key, dataSnapshot.getValue().toString());

                            break;
                    }


                    r.update();
                }catch(Exception ignored){}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}
