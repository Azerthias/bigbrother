package fr.smb.bigbrother.foyer.midi;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Util;

public class Pass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass);

        Util.setTitle(this,"Pass");

        ImageView imageView = findViewById(R.id.ivPass);
        Glide.with(this).load(R.drawable.pass).into(imageView);
    }
}
