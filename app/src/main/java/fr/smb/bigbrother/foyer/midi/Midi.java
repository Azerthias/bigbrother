package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
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

            TextView tv = new TextView(this);
            tv.setText(Util.jours[i]);
            tv.setWidth(200);

            Button b = new Button(this);
            b.setLayoutParams(param);



            final String path = "foyer/midi/semaines/semaine33/" + Util.jours[i] + "/" + 11 + "h/places";
            Reader.readOnTv(path,b,getString(R.string.texteBoutonFoyerMidi));

            b.setOnClickListener(v -> {
                Intent intent = new Intent(Midi.this, DemandeMidi.class);
                intent.putExtra("h", 11);
                intent.putExtra("j", jour);
                startActivity(intent);
            });

            Button b2 = new Button(this);
            b2.setLayoutParams(param);

            b2.setOnClickListener(v -> {
                Intent intent = new Intent(Midi.this, DemandeMidi.class);
                intent.putExtra("h", 12);
                intent.putExtra("j", jour);
                startActivity(intent);
            });

            ll2.addView(tv);
            ll2.addView(b);
            ll2.addView(b2);
            ll.addView(ll2);
        }

    }


}
