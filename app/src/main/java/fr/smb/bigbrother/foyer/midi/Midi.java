package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.Util;

public class Midi  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.midi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Manger au Foyer");


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
            bouton(b,Util.jours[i] + " : 11h - 12h");

            b.setOnClickListener(v -> {
                Intent intent = new Intent(Midi.this, DemandeMidi.class);
                intent.putExtra("h", 11);
                intent.putExtra("j", jour);
                startActivity(intent);
            });

            Button b2 = new Button(this);
            b2.setLayoutParams(param);
            bouton(b2,Util.jours[i] + " : 12h - 13h");

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Midi.this, DemandeMidi.class);
                    intent.putExtra("h", 12);
                    intent.putExtra("j", jour);
                    startActivity(intent);
                }
            });

            ll2.addView(tv);
            ll2.addView(b);
            ll2.addView(b2);
            ll.addView(ll2);
        }

    }

    public static void bouton(Button b,String id){

        try {
            Util.getDatabase().getReference("foyer").child("midi").child("places").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    b.setText(dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });
        }catch(Exception e){

        }
    }
}
