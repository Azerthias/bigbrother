package fr.smb.bigbrother.annexe.contact;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;

public class Probleme extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.probleme);

        Button bEnvoyer = findViewById(R.id.bEnvoyerProbleme);
        bEnvoyer.setOnClickListener(v -> {


        });
    }
}
