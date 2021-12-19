package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;
import fr.smb.bigbrother.util.database.read.Reader;

public class RemoveMidi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande);

        Intent intent = getIntent();
        int h = intent.getIntExtra("h", 0);
        int j = intent.getIntExtra("j", 0);

        TextView tv = findViewById(R.id.tvDemande);
        final String path = Util.dayPath(j, h) + "/places";
        final String text = "Retirer manger au foyer " + Util.jours[j] + " de " + h + "h à " + (h + 1) + "h";
        Reader.readOnTv(path,tv,text);

        Button b = findViewById(R.id.bOui);
        b.setOnClickListener(v -> {
            Database.remove(Util.dayPath(j, h)+ "/demandes/" + Cache.getCard());
            finish();
        });

        Button bRetour = findViewById(R.id.bNon);
        bRetour.setOnClickListener(v -> finish());

    }
}