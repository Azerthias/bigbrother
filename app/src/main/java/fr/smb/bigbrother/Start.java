package fr.smb.bigbrother;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class Start extends AppCompatActivity {

    final static String version = "beta";

    final static boolean forceLoggin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Util.init(this);

        Util.getDatabase().getReference("version").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String s = task.getResult().getValue(String.class);
                Log.println(Log.ASSERT, "g", "" + s);
                Intent i;
                if(s.equals(version)){
                    if(!Util.isLogged() || forceLoggin){
                        i = new Intent(Start.this, First.class);
                    }else {
                        i = new Intent(Start.this, MainActivity.class);
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
