package fr.smb.bigbrother.foyer.perm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Util;


public class Foyer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foyer);

        Util.setTitle(this,Util.getDay());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        LinearLayout ll = findViewById(R.id.ll);


        //myRef.setValue("Hello, World!");
        for(int i = 0; i < 9; i++) {

            if(i < 3 || i > 4) {
                final int heure = i + 8;

                DatabaseReference myRef = database.getReference("B" + i);

                LinearLayout ll2 = new LinearLayout(this);
                ll2.setOrientation(LinearLayout.HORIZONTAL);

                TextView tv = new TextView(this);
                tv.setText("De " + heure + "h à " + (heure+1) + "h : ");
                tv.setWidth(250);

                Button b = new Button(this);
                b.setHeight(150);
                b.setWidth(1000);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Foyer.this, Demande.class);
                        i.putExtra("h", heure);//
                        startActivity(i);
                    }
                });

                ll2.addView(tv);
                ll2.addView(b);
                ll.addView(ll2);


                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);

                        b.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });

            }else{
                i++;
                TextView tv = new TextView(this);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));
                tv.setText("midi");
                tv.setTextSize(30);
                tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                ll.addView(tv);
            }

        }

    }
}
