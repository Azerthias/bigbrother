package fr.smb.bigbrother.foyer.midi;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.smb.bigbrother.R;
import fr.smb.bigbrother.util.Util;

public class Pass extends AppCompatActivity {

    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass);

        Util.setTitle(this,"Pass");

        TextView tv = findViewById(R.id.tvPass);

        Handler handler = new Handler();



        int delay = 1000;
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Date dt = new Date();
                SimpleDateFormat dateFormat;
                dateFormat = new SimpleDateFormat("dd/MM\nkk:mm:ss");
                tv.setText(dateFormat.format(dt));
            }
        }, delay);

        ImageView imageView = findViewById(R.id.ivPass);
        Glide.with(this).load(R.drawable.pass).into(imageView);
    }
}
