package fr.smb.bigbrother.util.database.read.type;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;
import fr.smb.bigbrother.util.database.read.Reader;
import fr.smb.bigbrother.util.database.read.out;

public class text {
    /*public text(String path, String key, out out, Reader r){
        Util.print(path);
        out.addString(key,null);
        Database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    out.updateString(key, dataSnapshot.getValue().toString());
                    r.update();
                }catch(Exception ignored){}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }*/
}
