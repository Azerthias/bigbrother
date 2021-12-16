package fr.smb.bigbrother;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.foyer.Foyer;
import fr.smb.bigbrother.foyer.midi.Midi;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Big brother");



        Button b = findViewById(R.id.bMenu);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Menu.class);
                startActivity(i);
            }
        });

        Button bFoyer = findViewById(R.id.bFoyer);
        bFoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Foyer.class);
                startActivity(i);
            }
        });

        Button bFoyerMidi = findViewById(R.id.bFoyerMidi);
        bFoyerMidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Midi.class);
                startActivity(i);
            }
        });
    }
}
