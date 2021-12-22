package fr.smb.bigbrother.annexe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Util;

public class Maj  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maj);

        Util.setTitle(this,"Mise à jour nécessaire");

    }
}
