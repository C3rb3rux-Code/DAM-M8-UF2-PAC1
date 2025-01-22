package com.example.m8_uf2_pac1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RepSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repr_song);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        //Bitmap photo = (Bitmap) intent.getParcelableExtra("SongImage");
        String name = intent.getStringExtra("SongName");
        String time = intent.getStringExtra("SongTime");

        ImageView photoMusic = (ImageView)findViewById(R.id.songImage);
        TextView titleMusic = (TextView)findViewById(R.id.titleSong2);
        ImageButton startMusic = (ImageButton)findViewById(R.id.pauseButton);
        ImageButton beforeSong = (ImageButton)findViewById(R.id.backButton);
        ImageButton nextSong = (ImageButton)findViewById(R.id.nextButton);

        titleMusic.setText(name);
        //photoMusic.setImageBitmap(photo);

    }
}