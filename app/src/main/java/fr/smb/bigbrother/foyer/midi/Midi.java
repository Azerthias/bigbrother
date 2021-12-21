package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.read.Reader;
import fr.smb.bigbrother.util.database.read.Synchronizer;

public class Midi  extends AppCompatActivity {

    static int inscriptions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.midi);

        Util.setTitle(this,"Manger au foyer");


        LinearLayout ll = findViewById(R.id.llFoyerMidi);

        Button pass = findViewById(R.id.bPass);
        pass.setOnClickListener(v -> {
            Intent intent = new Intent(Midi.this, Pass.class);
            intent.putExtra("h", Util.getHour()+10);
            intent.putExtra("j", Util.getDay());
            startActivity(intent);
        });

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param.weight = 0.5f;
        param.setMargins(10,10,10,10);
        Synchronizer sync = new Synchronizer();

        Reader read = new Reader("principal");
        read.addSynchronizer(sync);
        int num = 0;


        for(int i = 0; i < 5; i++) {


            if(i == 2){i++;}
            final int jour = i;
            final int jv = jour>2?jour-1:jour;

            LinearLayout ll2 = new LinearLayout(this);
            ll2.setOrientation(LinearLayout.HORIZONTAL);
            ll2.setWeightSum(1);
            ll2.setLayoutParams(param);
            if(Util.getDayNumber() == i){
                ll2.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }

            TextView tv = new TextView(this);
            tv.setText(Util.jours[i]);
            tv.setWidth(200);
            ll2.addView(tv);



            for(int h = 11; h <= 12; h++){



                final int heure = h;
                final int hv = heure - 11;
                final String path = Util.dayPath(jour, heure);

                Button b = new Button(this);
                b.setLayoutParams(param);
                b.setTextSize(20);

                Reader r = new Reader(Util.jours[jour] + " -> " + heure + "h");
                r.setEvent(out -> {
                    int maxPlaces = out.getInt("value");
                    int nbPlaces = maxPlaces - out.getInt("count");
                    String text;
                    if (nbPlaces == 0){
                        text = "Créneau plein ";
                    }else if (nbPlaces == 1){
                        text = nbPlaces + " place restante";
                    }else {

                        text = nbPlaces + " places restantes";
                    }

                    boolean contain = out.getBoolean("contain");
                    b.setText(text);
                    if(contain){
                        SpannableString str = new SpannableString("inscrit\n\n"+text);
                        str.setSpan(new StyleSpan(Typeface.BOLD), 0, 7, 0);
                        str.setSpan(new UnderlineSpan(), 0, 7, 0);
                        str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_500)), 0, 7, 0);

                        b.setText(str);
                    }

                    if(!out.getBoolean("ouvert")){
                        b.setBackgroundColor(Color.DKGRAY);
                    }else if(nbPlaces <= 0){
                        b.setBackgroundColor(Color.RED);
                    }else if(nbPlaces <= 10){
                        b.setBackgroundColor(Color.parseColor("#ff9900"));
                    }else{
                        b.setBackgroundColor(Color.parseColor("#33cc33"));
                    }
                    if(!out.getBoolean("ouvert")){
                        b.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Créneau fermé",Toast.LENGTH_SHORT).show());
                    }else if(contain){
                        b.setOnClickListener(v -> {
                            Intent intent = new Intent(Midi.this, RemoveMidi.class);
                            intent.putExtra("h", heure);
                            intent.putExtra("j", jour);
                            startActivity(intent);
                        });


                    }else if (nbPlaces <=0){
                        b.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Créneau plein",Toast.LENGTH_SHORT).show());

                    }else if(inscriptions >= 1) {
                        b.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Tu es déjà inscrit cette semaine",Toast.LENGTH_SHORT).show());




                    }else{
                        b.setOnClickListener(v -> {
                            Intent intent = new Intent(Midi.this, DemandeMidi.class);
                            intent.putExtra("h", heure);
                            intent.putExtra("j", jour);
                            startActivity(intent);
                        });
                    }


                });
                r.addIntValue(path + "/places","value");
                r.addCount(path + "/demandes","count");
                r.addTest(path + "/demandes","contain" , "" + Cache.getCard());
                r.addBooleanValue(path + "/ouvert","ouvert");
                r.addSynchronizer(sync);
                ll2.addView(b);

                read.addTest(path + "/demandes","contain" + num , "" + Cache.getCard());
                num++;
            }


            ll.addView(ll2,ll.getChildCount() - 1);
        }

        read.setEvent(out -> {
            inscriptions = 0;
            for(int i = 0; i < 8; i++){
                if(out.getBoolean("contain" + i)){
                    inscriptions++;
                }
            }
            Util.print("" + inscriptions);
        });

    }



}
