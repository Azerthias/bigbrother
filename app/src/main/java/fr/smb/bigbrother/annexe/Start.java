package fr.smb.bigbrother.annexe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import fr.smb.bigbrother.MainActivity;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;

public class Start extends AppCompatActivity {

    final static String version = "beta";

    final static boolean forceLoggin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Util.init(this);

        Database.getReference("version").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String s = task.getResult().getValue(String.class);
                Log.println(Log.ASSERT, "g", "" + s);
                Intent i;
                if(s.equals(version)){

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if(currentUser == null){
                        Util.print("null");
                        i = new Intent(Start.this, Authentification.class);
                    }else if(!currentUser.getEmail().split("@")[1].equals("stemariebeaucamps.fr")){
                        i = new Intent(Start.this, Authentification.class);
                    }else{
                        if(!Cache.isLogged() || forceLoggin){
                            i = new Intent(Start.this, First.class);
                        }else {
                            i = new Intent(Start.this, MainActivity.class);
                        }
                    }


                }else{

                    i = new Intent(Start.this, Maj.class);
                }
                startActivity(i);
                finish();
            }
        });

    }
}
