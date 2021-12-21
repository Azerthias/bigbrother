package fr.smb.bigbrother.util.database.read.type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;
import fr.smb.bigbrother.util.database.read.Reader;
import fr.smb.bigbrother.util.database.read.out;

public class count {

    int number = 0;

    public count(String path, String key, out out, Reader r){
        out.addInt(key,0);
        try {
            Database.getReference(path).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    number++;
                    out.updateInt(key, number);
                    r.update();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    number--;
                    out.updateInt(key, number);
                    r.update();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch(NullPointerException ignored){}
    }
}
