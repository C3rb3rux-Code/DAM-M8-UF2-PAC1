package com.example.m8_uf2_pac1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_AUDIO};
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private final ListView songList = findViewById(R.id.songListView);
    private final MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        } else {
            File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            ArrayList<Song> songs = new ArrayList<>();

            if (downloadFolder.exists() && downloadFolder.isDirectory()) {
                for (File file : Objects.requireNonNull(downloadFolder.listFiles())) {
                    if (file.getName().endsWith(".mp3")) {

                        Bitmap imagePCP = null;

                        try {
                            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                            retriever.setDataSource(file.getAbsolutePath());

                            String titlePCP = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                            String artistPCP = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                            String albumPCP = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                            String durationPCP = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

                            byte[] artBytes = retriever.getEmbeddedPicture();
                            if (artBytes != null) {
                                imagePCP = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
                            }

                            songs.add(new Song(imagePCP, file.getAbsolutePath(), durationPCP, titlePCP, artistPCP, albumPCP));

                            retriever.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
                ListAdapter adapterPCP = new ListAdapter(this, songs);
                songList.setAdapter(adapterPCP);
            }
        }
    }
}
