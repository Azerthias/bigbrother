package fr.smb.bigbrother.annexe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.MainActivity;
import fr.smb.bigbrother.Menu;
import fr.smb.bigbrother.R;
import fr.smb.bigbrother.foyer.midi.Midi;
import fr.smb.bigbrother.foyer.perm.Foyer;
import fr.smb.bigbrother.util.Util;

public class Contact  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.setTitle(this,"Big brother");

        Button b = findViewById(R.id.bMenu);
        b.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Menu.class);
            startActivity(i);
        });

        Button bFoyer = findViewById(R.id.bFoyer);
        bFoyer.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Foyer.class);
            startActivity(i);
        });

        Button bFoyerMidi = findViewById(R.id.bFoyerMidi);
        bFoyerMidi.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Midi.class);
            startActivity(i);
        });

        Button bContact = findViewById(R.id.bProbleme);
        bContact.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Contact.class);
            startActivity(i);
        });
    }
}
