package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.Util;

public class DemandeMidi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande_midi);

        Intent intent = getIntent();
        int h = intent.getIntExtra("h",0);
        int j = intent.getIntExtra("j",0);

        TextView tv = findViewById(R.id.tvDemande);
        tv.setText("Demander à manger foyer " + Util.jours[j] + " de " + h + "h à " + (h+1) + "h");

        Button b = findViewById(R.id.bDemande);
        b.setOnClickListener(v -> {
            //Util.write("foyer","truc");
            Map<String, Object> updates = new HashMap<String,Object>();
            updates.put("" + Util.getCard(), Util.getName());
            Util.getDatabase().getReference("foyer").child("demandes").child(Util.jours[j]).child(h + "h").updateChildren(updates);



                   // .setValue("test" + h);//(Object)Util.getName());
            //Util.print("name", Util.getName());
            finish();
        });

        Button bRetour = findViewById(R.id.bRetour);
        bRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
