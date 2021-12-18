package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Reader;

public class Midi  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.midi);

        Util.setTitle(this,"Manger au Foyer");


        LinearLayout ll = findViewById(R.id.llFoyerMidi);


        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param.weight = 0.5f;

        for(int i = 0; i < 5; i++) {


            if(i == 2){i++;}
            final int jour = i;

            LinearLayout ll2 = new LinearLayout(this);
            ll2.setOrientation(LinearLayout.HORIZONTAL);
            ll2.setWeightSum(1);
            ll2.setLayoutParams(param);
            if(Util.getDayNumber() == i){
                ll2.setBackgroundColor(Color.BLUE);
            }

            TextView tv = new TextView(this);
            tv.setText(Util.jours[i]);
            tv.setWidth(200);
            ll2.addView(tv);

            for(int h = 11; h <= 12; h++){

                final int heure = h;
                final String path = Util.dayPath(jour, heure) + "/places";
                final String text = getString(R.string.texteBoutonFoyerMidi);

                Button b = new Button(this);
                b.setLayoutParams(param);
                b.setOnClickListener(v -> {
                    Intent intent = new Intent(Midi.this, DemandeMidi.class);
                    intent.putExtra("h", heure);
                    intent.putExtra("j", jour);
                    startActivity(intent);
                });

                Reader.readOnTv(path, b, text);

                ll2.addView(b);
            }

            ll.addView(ll2);
        }

    }


}
