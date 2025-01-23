package com.example.m8_uf2_pac1;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Song> {

    MediaPlayer mp = new MediaPlayer();
    private boolean isImage1 = true;
    boolean isPlaying;

    public ListAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    private static class ViewHolder {
        ImageView imageSongPCP;
        TextView nameSongPCP;
        TextView timeSongPCP;
        ImageButton startButtonPCP;
        LinearLayout layoutPCP;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Song songPCP = getItem(position);
        ViewHolder viewHolderPCP;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_adapter, parent, false);

            viewHolderPCP = new ViewHolder();
            viewHolderPCP.imageSongPCP = (ImageView)convertView.findViewById(R.id.songPhoto);
            viewHolderPCP.nameSongPCP = (TextView) convertView.findViewById(R.id.songName);
            viewHolderPCP.timeSongPCP = (TextView)convertView.findViewById(R.id.songTime);
            viewHolderPCP.layoutPCP = (LinearLayout)convertView.findViewById(R.id.layoutAdapt);
            viewHolderPCP.startButtonPCP = (ImageButton)convertView.findViewById(R.id.playButton);

            convertView.setTag(viewHolderPCP);
        } else {
            viewHolderPCP = (ViewHolder) convertView.getTag();
        }

        if (songPCP != null) {
            viewHolderPCP.nameSongPCP.setText(songPCP.namePCP);
            viewHolderPCP.timeSongPCP.setText(songPCP.timePCP);
            viewHolderPCP.imageSongPCP.setImageBitmap(songPCP.imagePCP);
        }

        viewHolderPCP.layoutPCP.setOnClickListener(view -> {
            Intent intent = new Intent(ListAdapter.this.getContext(), RepSong.class);
            if (songPCP != null) {
                intent.putExtra("SongName", songPCP.namePCP);
                //intent.putExtra("SongImage", songPCP.imagePCP);
                intent.putExtra("SongTime", songPCP.timePCP);
                getContext().startActivity(intent);
            }
        });

        assert songPCP != null;
        File filePCP = new File(songPCP.pathPCP);
        viewHolderPCP.startButtonPCP.setImageResource(R.drawable.tocar);

        viewHolderPCP.startButtonPCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImage1) {
                    viewHolderPCP.startButtonPCP.setImageResource(R.drawable.pausa);
                } else {
                    viewHolderPCP.startButtonPCP.setImageResource(R.drawable.tocar);
                }
                isImage1 = !isImage1;
                if (filePCP.exists()) {
                    if (mp == null) {
                        mp = new MediaPlayer();
                    }
                    mp.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );

                    try {
                        if (mp.isPlaying()) {
                            mp.pause();
                            isPlaying = false;
                        } else {
                            mp.reset();
                            mp.setDataSource(filePCP.getAbsolutePath());
                            mp.prepare();
                            mp.start();
                            isPlaying = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("El archivo de audio no existe.");
                }
            }
        });

        return convertView;
    }
}
