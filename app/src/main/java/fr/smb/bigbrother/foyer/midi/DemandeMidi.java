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

public class DemandeMidi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande);

        Intent intent = getIntent();
        int h = intent.getIntExtra("h", 0);
        int j = intent.getIntExtra("j", 0);

        TextView tv = findViewById(R.id.tvDemande);
        final String path = Util.dayPath(j, h);

        Reader r = new Reader();
        r.setEvent(out -> {
            int maxPlaces = out.getInt("value");
            int nbPlaces = maxPlaces - out.getInt("count");
            String text = "Veux-tu t'inscrire pour manger au foyer " + Util.jours[j] + " de " + h + "h à " + (h + 1) + "h ?\n\nIl reste " + nbPlaces + " place(s)" ;
            tv.setText(text);



        });
        r.addIntValue(path + "/places","value");
        r.addCount(path + "/demandes","count");

        Button b = findViewById(R.id.bOui);
        b.setOnClickListener(v -> {
            Database.write(Util.dayPath(j, h)+ "/demandes/" + Cache.getCard(), Cache.getName());
            finish();
        });

        Button bRetour = findViewById(R.id.bNon);
        bRetour.setOnClickListener(v -> finish());

    }
}