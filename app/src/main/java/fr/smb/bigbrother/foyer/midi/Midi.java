package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.read.Reader;

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
        param.setMargins(10,10,10,10);

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
                final String path = Util.dayPath(jour, heure);
                //final String text = getString(R.string.texteBoutonFoyerMidi);

                Button b = new Button(this);
                b.setLayoutParams(param);
                b.setTextSize(20);

                Reader r = new Reader();
                r.setEvent(out -> {
                    int maxPlaces = out.getInt("value");
                    int nbPlaces = maxPlaces - out.getInt("count");
                    String text = "il reste " + nbPlaces + " places sur " + maxPlaces;
                    boolean contain = out.getBoolean("contain");
                    b.setText(text);
                    if(contain){
                        b.setBackgroundColor(Color.GREEN);
                    }else if(nbPlaces <= 0){
                        b.setBackgroundColor(Color.RED);
                    }else if(nbPlaces <= 10){
                        b.setBackgroundColor(Color.YELLOW);
                    }else{
                        b.setBackgroundColor(Color.BLUE);
                    }

                    if(contain || nbPlaces <= 0){
                        b.setOnClickListener(v -> {
                            Intent intent = new Intent(Midi.this, RemoveMidi.class);
                            intent.putExtra("h", heure);
                            intent.putExtra("j", jour);
                            startActivity(intent);
                        });
                    }else{
                        b.setOnClickListener(v -> {
                            Intent intent = new Intent(Midi.this, DemandeMidi.class);
                            intent.putExtra("h", heure);
                            intent.putExtra("j", jour);
                            startActivity(intent);
                        });
                    }


                });
                r.addValue(path + "/places","value");
                r.addCount(path + "/demandes","count");
                r.addTest(path + "/demandes","contain" , "" + Cache.getCard());

                ll2.addView(b);
            }

            ll.addView(ll2);
        }

    }


}
