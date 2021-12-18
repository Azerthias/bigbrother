package fr.smb.bigbrother.annexe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.MainActivity;
import fr.smb.bigbrother.R;

public class First extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        Button b = findViewById(R.id.bAuthValider);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("prefData", Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = preferences.edit();
                EditText edNom = findViewById(R.id.edNom);
                EditText edPrenom = findViewById(R.id.edPrenom);
                EditText edCodeBar = findViewById(R.id.edCodeBar);

                String name = edNom.getText().toString() + " " + edPrenom.getText().toString();
                int tag = Integer.parseInt(edCodeBar.getText().toString());

                editor.putString("name",name);
                editor.putInt("tag",tag);
                editor.putBoolean("logged" ,true);
                editor.apply();
                Intent i = new Intent(First.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
