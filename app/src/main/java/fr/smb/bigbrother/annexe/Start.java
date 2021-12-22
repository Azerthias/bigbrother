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

import fr.smb.bigbrother.BuildConfig;
import fr.smb.bigbrother.MainActivity;
import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;

public class Start extends AppCompatActivity {

    final static boolean forceAuth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        Util.init(this);

        Database.getReference("version").get().addOnCompleteListener(task -> {
            String s = task.getResult().getValue(String.class);
            Log.println(Log.ASSERT, "version", "" + s);
            Intent i;

            if(s.equals(BuildConfig.VERSION_NAME)){

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(!Cache.isLogged() || currentUser == null || forceAuth || !currentUser.getEmail().split("@")[1].equals("stemariebeaucamps.fr")){
                    Util.print("null");
                    i = new Intent(Start.this, Authentification.class);
                }else{
                    i = new Intent(Start.this, MainActivity.class);

                }


            }else{

                i = new Intent(Start.this, Maj.class);
            }
            startActivity(i);
            finish();
        });

    }
}
