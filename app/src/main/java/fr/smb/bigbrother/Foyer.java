package fr.smb.bigbrother;

import android.os.Bundle;
import android.util.Log;
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

import fr.smb.bigbrother.R;

public class Foyer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foyer);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Planning Foyer");
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        LinearLayout ll = findViewById(R.id.ll);

       TextView tv = new TextView(this);
        tv.setHeight(100);
tv.setText("Mardi");

        ll.addView(tv);

        //myRef.setValue("Hello, World!");
        for(int i = 0; i < 8; i++) {
            DatabaseReference myRef = database.getReference("B" + i);


            Button b = new Button(this);
            b.setHeight(200);


            ll.addView(b);


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

        }

    }
}
