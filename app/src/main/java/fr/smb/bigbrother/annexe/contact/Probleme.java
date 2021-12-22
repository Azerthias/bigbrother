package fr.smb.bigbrother.annexe.contact;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.Database;

public class Probleme extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.probleme);

        EditText ed = findViewById(R.id.edProbleme);
        Button bEnvoyer = findViewById(R.id.bEnvoyerProbleme);
        bEnvoyer.setOnClickListener(v -> {
            if (ed.getText().toString().length()!=0){
                Database.write("probleme/"+ Cache.getName()+"/"+ Util.getTime(),ed.getText().toString());
                Toast.makeText(getApplicationContext(),"Envoyé",Toast.LENGTH_SHORT).show();
                finish();




            }




        });
    }
}
