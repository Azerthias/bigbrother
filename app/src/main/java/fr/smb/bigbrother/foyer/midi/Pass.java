package fr.smb.bigbrother.foyer.midi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Cache;
import fr.smb.bigbrother.util.Util;
import fr.smb.bigbrother.util.database.read.Reader;

public class Pass extends AppCompatActivity {

    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass);

        Intent intent = getIntent();
        int h = intent.getIntExtra("h", 0);
        int j = intent.getIntExtra("j", 0);

        Util.setTitle(this,"Pass");

        TextView tv = findViewById(R.id.tvPass);

        Handler handler = new Handler();
        handler.postDelayed(runnable = () -> {
            handler.postDelayed(runnable, 1000);
            Date dt = new Date();
            SimpleDateFormat dateFormat;
            dateFormat = new SimpleDateFormat("dd/MM\nkk:mm:ss");
            tv.setText(dateFormat.format(dt));
        }, 0);

        ImageView imageView = findViewById(R.id.ivPass);
        Reader r = new Reader("pass");
        r.addTest(Util.dayPath(j,h) + "/demandes" ,"inscrit", ""+Cache.getCard());
        r.setEvent(out -> {
            if  (out.getBoolean("inscrit")) {
                Glide.with(Pass.this).load(R.drawable.pass).into(imageView);
            }else{
                Glide.with(Pass.this).load(R.drawable.croix).into(imageView);
            }


        });




    }
}
