package fr.smb.bigbrother.foyer.perm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.database.Database;

public class Demande extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande);

        Intent intent = getIntent();
        int h = intent.getIntExtra("h",0);

        TextView tv = findViewById(R.id.tvDemande);
        tv.setText("Souhaites-tu aller au foyer de " + (h+8) + "h à " + (h+9) +"h ?");

        Button b = findViewById(R.id.bOui);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.write("B" + h,"TC");
                finish();
            }
        });

        Button bRetour = findViewById(R.id.bNon);
        bRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
