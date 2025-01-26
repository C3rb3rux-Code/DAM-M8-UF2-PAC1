package com.example.m8_uf2_pac1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

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
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_AUDIO};
    private ActivityResultLauncher<String> requestPermissionLauncher;
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
            File downloadFolderPCP = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            ArrayList<Song> songsPCP = new ArrayList<>();

            if (downloadFolderPCP.exists() && downloadFolderPCP.isDirectory()) {
                for (File file : Objects.requireNonNull(downloadFolderPCP.listFiles())) {
                    if (file.getName().endsWith(".mp3") || file.getName().endsWith(".m4a") ||file.getName().endsWith(".mp4")) {

                        Bitmap imagePCP = null;

                        try {
                            MediaMetadataRetriever retrieverPCP = new MediaMetadataRetriever();
                            retrieverPCP.setDataSource(file.getAbsolutePath());

                            String titlePCP = retrieverPCP.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                            String artistPCP = retrieverPCP.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                            String albumPCP = retrieverPCP.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                            String durationPCP = retrieverPCP.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

                            byte[] artBytesPCP = retrieverPCP.getEmbeddedPicture();
                            if (artBytesPCP != null) {
                                imagePCP = BitmapFactory.decodeByteArray(artBytesPCP, 0, artBytesPCP.length);
                            }

                            assert durationPCP != null;
                            int durationToIntPCP = Integer.parseInt(durationPCP);

                            long minutes = (durationToIntPCP / 1000) / 60;
                            long seconds = (durationToIntPCP / 1000) % 60;

                            String timePCP =  String.format("%02d:%02d", minutes, seconds);

                            songsPCP.add(new Song(imagePCP, file.getAbsolutePath(), titlePCP, timePCP,  artistPCP, albumPCP));

                            retrieverPCP.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                ListView songList = findViewById(R.id.songListView);
                ListAdapter adapterPCP = new ListAdapter(this, songsPCP);
                songList.setAdapter(adapterPCP);;
            }
        }
    }
}
