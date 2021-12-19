package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.read.Reader;

public class Midi  extends AppCompatActivity {

    static boolean[][] inscrit = new boolean[4][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.midi);

        Util.setTitle(this,"Manger au Foyer");


        LinearLayout ll = findViewById(R.id.llFoyerMidi);

        Button pass = findViewById(R.id.bPass);
        pass.setOnClickListener(v -> {
            Intent i = new Intent(Midi.this, Pass.class);
            startActivity(i);
        });

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param.weight = 0.5f;
        param.setMargins(10,10,10,10);



        for(int i = 0; i < 5; i++) {


            if(i == 2){i++;}
            final int jour = i;
            final int jv = jour>2?jour-1:jour;
            Util.print("jv : " + jv);

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
                final int hv = heure - 11;
                final String path = Util.dayPath(jour, heure);

                Button b = new Button(this);
                b.setLayoutParams(param);
                b.setTextSize(20);

                Reader r = new Reader();
                r.setEvent(out -> {
                    int maxPlaces = out.getInt("value");
                    int nbPlaces = maxPlaces - out.getInt("count");
                            String text;
                            if (nbPlaces == 0) {
                                text = " créneau plein ";
                            }

                            else if (nbPlaces == 1){
                                    text = nbPlaces + " place restante";
                                }
                            else {

                                text = nbPlaces + " places restantes";
                            }

                    boolean contain = out.getBoolean("contain");
                    b.setText(text);
                    if(contain){
                        inscrit[jv][hv] = true;
                        SpannableString str = new SpannableString("inscrit\n\n"+text);
                        str.setSpan(new UnderlineSpan(), 0, 7, 0);
                        str.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.purple_500)), 0, 7, 0);

                        b.setText(str);

                    }else{
                        inscrit[jv][hv] = false;

                    }

                    if(nbPlaces <= 0){
                        b.setBackgroundColor(Color.RED);
                    }else if(nbPlaces <= 10){
                        b.setBackgroundColor(Color.parseColor("#ff9900"));
                    }else{
                        b.setBackgroundColor(Color.parseColor("#33cc33"));
                    }

                    if(contain){
                        b.setOnClickListener(v -> {
                            Intent intent = new Intent(Midi.this, RemoveMidi.class);
                            intent.putExtra("h", heure);
                            intent.putExtra("j", jour);
                            startActivity(intent);
                        });


                    }else if (nbPlaces <=0){
                        b.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Crénau plein",Toast.LENGTH_SHORT).show());

                    }else if(nbInscription() >= 1) {
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
                r.addValue(path + "/places","value");
                r.addCount(path + "/demandes","count");
                r.addTest(path + "/demandes","contain" , "" + Cache.getCard());

                ll2.addView(b);
            }


            ll.addView(ll2,ll.getChildCount() - 1);
        }

    }

    private static int nbInscription(){
        int nb = 0;
        for(boolean[] b : inscrit){
            for(boolean b2 : b){
                if(b2){
                    nb++;
                }
            }
        }
        return nb;
    }


}
